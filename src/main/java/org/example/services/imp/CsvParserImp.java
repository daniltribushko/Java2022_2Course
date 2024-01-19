package org.example.services.imp;

import org.example.models.csv.Module;
import org.example.models.csv.Student;
import org.example.models.csv.Task;
import org.example.models.enums.TaskType;
import org.example.services.CsvParser;

import java.io.File;
import java.io.FileNotFoundException;
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

    @Override
    public List<Module> getModuleFromCsv(Integer group) {
        List<Module> result = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(FILES_DIRECTORY + FILE_NAME + group + EXTENSION));
            String[] modules = scanner.nextLine().split(";");
            String[] tasks = scanner.nextLine().split(";");
            String[] tasksMaxScores = scanner.nextLine().split(";");
            List<Task> taskList = new ArrayList<>();
            Map<Integer, Task> tasksWithIndex = new TreeMap<>();
            for (int i = tasks.length - 3; i > 6; i--){
                String moduleString = modules[i];
                if (moduleString.isEmpty()) {
                    Task task = getTask(tasks[i], tasksMaxScores[i]);
                    if (task != null) {
                        tasksWithIndex.put(i, task);
                        taskList.add(task);
                    }
                } else {
                    int maxScoreByHomework = 0;
                    int maxScoreByControlQuestion = 0;
                    int maxScoreByExercise = 0;
                    for (Task task : taskList){
                        Integer maxScore = task.getMaxScore();
                        switch (task.getType()){
                            case EXERCISE -> maxScoreByExercise += maxScore;
                            case HOMEWORK -> maxScoreByHomework += maxScore;
                            case CONTROL_QUESTION -> maxScoreByControlQuestion += maxScore;
                        }
                    }
                    Module module = new Module(moduleString,
                            maxScoreByHomework,
                            maxScoreByControlQuestion,
                            maxScoreByExercise,
                            taskList);
                    taskList = new ArrayList<>();
                    result.add(module);
                }
            }

        } catch (FileNotFoundException e){
            System.out.println("!!!Ошибка!!! - " + e.getMessage());

        }
        return result;
    }

    @Override
    public List<Student> getStudentsFromCsv(Integer group) {
        return null;
    }


    private Task getTask(String taskNameString, String taskScoreString){
        String[] taskNameMas = taskNameString.split(":");
        if (taskNameMas.length != 2){
            return null;
        } else {
            TaskType type = null;
            switch (taskNameMas[0]){
                case "ДЗ" -> type = TaskType.HOMEWORK;
                case "КВ" -> type = TaskType.CONTROL_QUESTION;
                case "УПР" -> type = TaskType.EXERCISE;
            }
            return new Task(taskNameMas[1], type, Integer.parseInt(taskScoreString));
        }
    }

}
