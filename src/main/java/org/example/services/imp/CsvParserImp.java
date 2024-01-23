package org.example.services.imp;

import org.example.models.csv.*;
import org.example.models.csv.ModuleCsv;
import org.example.models.enums.TaskType;
import org.example.services.CsvParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * @author Tribushko Danil
 * @since 18.01.2024
 * <p>
 * Реализация интерфейса csv парсера
 */
public class CsvParserImp implements CsvParser {
    /**
     * Адрес директории с csv файлами
     */
    private static final String FILES_DIRECTORY = "src/main/resources/csv/";
    /**
     * Общая часть имени файлов
     */
    private static final String FILE_NAME = "java-rtf-";
    /**
     * Расширение файлов
     */
    private static final String EXTENSION = ".csv";
    private final List<StudentCsv> students;

    public CsvParserImp() {
        students= new ArrayList<>();
    }

    /**
     * Получение списка модулей икурса из csv файла
     * @param group номер группы
     * @return список модулей
     */
    @Override
    public List<ModuleCsv> getModuleFromCsv(Integer group) {
        List<ModuleCsv> result = new ArrayList<>();
        try {
            //Сканируем файл
            Scanner scanner = new Scanner(new BufferedReader(
                    new FileReader(FILES_DIRECTORY + FILE_NAME + group + EXTENSION)));
            //Парсим первую строку, в которой содержатся названия модулей
            String[] modules = scanner.nextLine().split(";");
            //Парсим вторую строку, в которой содержатся названия заданий
            String[] tasks = scanner.nextLine().split(";");
            //Парсим третью строку, в которой содержатся максимальные баллы за задания
            String[] tasksMaxScores = scanner.nextLine().split(";");
            List<TaskCsv> taskCsvList = new ArrayList<>();
            //Пропускаем последний модуль, т.к он без баллов студентов и информацию о студентеж
            for (int i = tasks.length - 3; i > 6; i--) {
                String moduleString = modules[i];
                //Если элемент из первой строки не содержит название модуля,
                // то получаем задание, иначе создаем модуль и обнуляем список заданий
                if (moduleString.isEmpty()) {
                    TaskCsv taskCsv = getTask(tasks[i], tasksMaxScores[i]);
                    if (taskCsv != null) {
                        taskCsvList.add(taskCsv);
                    }
                } else {
                    result.add(createModule(moduleString, taskCsvList));
                    taskCsvList = new ArrayList<>();
                }
            }
            //Получаем данные студента
            while (scanner.hasNext()) {
                //Парсим строку
                String[] mas = scanner.nextLine().split(";");
                //Создаем студента и добавляем его в список
                StudentCsv studentCsv = new StudentCsv(mas[0], mas[1], group);
                students.add(studentCsv);
                //Порядковый номер модуля
                int moduleNumber = 0;
                //Получаем модуль из списка
                ModuleCsv moduleCsv = result.get(moduleNumber);
                //Получаем список заданий модуля
                List<TaskCsv> moduleTaskCsvs = moduleCsv.getTasks();
                //Получаем индекс последнего задания
                int taskNumber = 0;
                int scoreByExercise = 0;
                int scoreByHomeWork = 0;
                int scoreByControlQuestion = 0;
                for (int i = tasks.length - 3; i > 6; i--) {
                    String masPart = mas[i];
                    String moduleString = modules[i];
                    if (moduleString.isEmpty()) {
                        switch (tasks[i]) {
                            case "ДЗ" -> scoreByHomeWork = Integer.parseInt(masPart);
                            case "УПР" -> scoreByExercise = Integer.parseInt(masPart);
                            case "КВ" -> scoreByControlQuestion = Integer.parseInt(masPart);
                            default -> {
                                TaskCsv taskCsv = moduleTaskCsvs.get(taskNumber);
                                taskCsv.addScore(new TaskScoreCsv(studentCsv, taskCsv, Integer.parseInt(mas[i])));
                                taskNumber++;
                            }
                        }
                    } else {
                        switch (tasks[i]) {
                            case "ДЗ" -> scoreByHomeWork = Integer.parseInt(masPart);
                            case "УПР" -> scoreByExercise = Integer.parseInt(masPart);
                            case "КВ" -> scoreByControlQuestion = Integer.parseInt(masPart);
                        }
                        moduleCsv.addScore(new ModuleScoreCsv(moduleCsv, studentCsv, scoreByHomeWork,
                                scoreByControlQuestion, scoreByExercise));
                        moduleNumber++;
                        if (moduleNumber == result.size()){
                            break;
                        }
                        moduleCsv = result.get(moduleNumber);
                        moduleTaskCsvs = moduleCsv.getTasks();
                        taskNumber = 0;
                        scoreByExercise = 0;
                        scoreByHomeWork = 0;
                        scoreByControlQuestion = 0;
                    }

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("!!!Ошибка!!! - " + e.getMessage());

        }
        result.forEach(m -> {
            List<TaskCsv> taskCsvs = m.getTasks();
            Collections.reverse(taskCsvs);
            m.setTasks(taskCsvs);});
        Collections.reverse(result);
        return result;
    }

    /**
     * Создание моудля
     * @param moduleName имя модуля
     * @param taskCsvList список заданий
     * @return модуль
     */
    private ModuleCsv createModule(String moduleName, List<TaskCsv> taskCsvList) {
        int maxScoreByHomework = 0;
        int maxScoreByControlQuestion = 0;
        int maxScoreByExercise = 0;
        for (TaskCsv taskCsv : taskCsvList) {
            Integer maxScore = taskCsv.getMaxScore();
            switch (taskCsv.getType()) {
                case EXERCISE -> maxScoreByExercise += maxScore;
                case HOMEWORK -> maxScoreByHomework += maxScore;
                case CONTROL_QUESTION -> maxScoreByControlQuestion += maxScore;
            }
        }
        return new ModuleCsv(moduleName,
                maxScoreByHomework,
                maxScoreByControlQuestion,
                maxScoreByExercise,
                taskCsvList);
    }

    /**
     * Создание задания
     *
     * @param taskNameString строка с названием задания
     * @param taskScoreString строка с максимальным количеством баллов за задание
     * @return задание
     */
    private TaskCsv getTask(String taskNameString, String taskScoreString) {
        //Парсим первую строку и проверяем что массив имеет 2 элемента, то есть строка имеет вид ТИП:Название
        String[] taskNameMas = taskNameString.split(":");
        if (taskNameMas.length != 2) {
            return null;
        } else {
            TaskType type = null;
            switch (taskNameMas[0]) {
                case "ДЗ" -> type = TaskType.HOMEWORK;
                case "КВ" -> type = TaskType.CONTROL_QUESTION;
                case "УПР" -> type = TaskType.EXERCISE;
            }
            return new TaskCsv(taskNameMas[1], type, Integer.parseInt(taskScoreString));
        }
    }

    @Override
    public List<StudentCsv> getStudents() {
        return students;
    }
}
