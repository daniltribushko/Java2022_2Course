package org.example.services.utils;

import org.example.models.entities.*;
import org.example.models.entities.Module;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import java.util.List;
import java.util.Map;

/**
 * @author Tribushko Danil
 * @since 29.01.2024
 * <p>
 * Класс для создания датасетов для создания графиков
 */
public class DatasetService {
    private DatasetService() {
    }

    /**
     * Динамика процента выполнения заданий в модулях, у каждой группы
     *
     * @param modules      список модулей
     * @param studentsData словарь со списком студентов и номером их группы
     * @return датасет
     */
    public static CategoryDataset createDatasetModuleScoresByGroup(List<Module> modules,
                                                                   Map<Integer, List<Student>> studentsData) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Module module : modules) {
            Integer maxScoreByExercise = module.getMaxScoreByExercise();
            Integer maxScoreByControlQuestion = module.getMaxScoreByControlQuestion();
            Integer maxScoreByHomeWork = module.getMaxScoreByHomeWork();
            int count = 0;
            if (maxScoreByExercise != 0) {
                count++;
            }
            if (maxScoreByControlQuestion != 0) {
                count++;
            }
            if (maxScoreByHomeWork != 0) {
                count++;
            }
            for (Map.Entry<Integer, List<Student>> data : studentsData.entrySet()) {
                List<Student> students = data.getValue();
                Integer countStudents = students.size();
                Integer maxStudentsScoreByExercise = maxScoreByExercise * countStudents;
                Integer maxStudentsScoreByControlQuestion = maxScoreByControlQuestion * countStudents;
                Integer maxStudentsScoreByHomeWork = maxScoreByHomeWork * countStudents;
                Integer studentsScoreByExercise = 0;
                Integer studentScoreByControlQuestion = 0;
                Integer studentScoreByHomeWork = 0;
                for (Student student : students) {
                    for (ModuleScore moduleScore : student.getModuleScores()) {
                        if (moduleScore.getModule().equals(module)) {
                            studentsScoreByExercise += moduleScore.getScoreByExercise();
                            studentScoreByControlQuestion += moduleScore.getScoreByControlQuestion();
                            studentScoreByHomeWork += moduleScore.getScoreByHomeWork();
                            break;
                        }
                    }
                }
                int percentageScores = getPercentage(maxStudentsScoreByExercise, studentsScoreByExercise) +
                        getPercentage(maxStudentsScoreByControlQuestion, studentScoreByControlQuestion) +
                        getPercentage(maxStudentsScoreByHomeWork, studentScoreByHomeWork);
                System.out.println(data.getKey() + " " + module.getName() + " " + percentageScores + " " + count);
                dataset.addValue(percentageScores / count,
                        data.getKey(),
                        module.getName());
            }
        }
        return dataset;
    }

    /**
     * Общая динамика баллов за задания в определенном модулей
     *
     * @param module модуль
     * @return датасет
     */
    public static CategoryDataset createScoresByTasksInModule(Module module) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Task task : module.getTasks()) {
            List<TaskScore> scores = task.getTaskScores();
            Integer maxStudentsScores = task.getMaxScore() * scores.size();
            Integer studentScores = 0;
            for (TaskScore score : scores) {
                studentScores += score.getScore();
            }
            dataset.addValue(getPercentage(maxStudentsScores, studentScores),
                    "",
                    task.getName());
        }
        return dataset;
    }

    /**
     * распределение баллов за курс среди групп студентов
     *
     * @param modules             список модулей
     * @param countStudentsGroup1 количество студентов в первой группе
     * @param countStudentsGroup2 количество студентов во второй группе
     * @param countStudentsGroup3 количество студентов в третьей группе
     * @return датасет
     */
    public static PieDataset<String> createDatasetDistributionScoresByGroups(
            List<Module> modules,
            int countStudentsGroup1,
            int countStudentsGroup2,
            int countStudentsGroup3) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        int scoresFirstGroup = 0;
        int scoresSecondGroup = 0;
        int scoresThirdGroup = 0;
        int maxScoreStudents = 0;
        for (Module module : modules) {
            maxScoreStudents += module.getMaxScoreByControlQuestion() +
                    module.getMaxScoreByExercise() +
                    module.getMaxScoreByHomeWork();
            for (ModuleScore score : module.getModuleScores()) {
                Student student = score.getStudent();
                switch (student.getGroup()) {
                    case 1 -> scoresFirstGroup += score.getScoreByExercise() +
                            score.getScoreByControlQuestion() +
                            score.getScoreByHomeWork();
                    case 2 -> scoresSecondGroup += score.getScoreByExercise() +
                            score.getScoreByControlQuestion() +
                            score.getScoreByHomeWork();
                    case 3 -> scoresThirdGroup += score.getScoreByExercise() +
                            score.getScoreByControlQuestion() +
                            score.getScoreByHomeWork();
                }
            }
        }
        dataset.setValue("Первая группа",
                getPercentage(maxScoreStudents * countStudentsGroup1,
                        scoresFirstGroup));
        dataset.setValue("Вторая группа",
                getPercentage(maxScoreStudents * countStudentsGroup2,
                        scoresSecondGroup));
        dataset.setValue("Третья группа",
                getPercentage(maxScoreStudents * countStudentsGroup3,
                        scoresThirdGroup));
        return dataset;
    }

    /**
     * Динамика баллов за упражнения, домашнии задания, контрольные вопросы среди модулей
     *
     * @param modules список модулей
     * @return датасет
     */
    public static CategoryDataset createDatasetModuleScores(List<Module> modules) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Integer countStudents = 62;
        for (Module module : modules) {
            Integer maxStudentsScoreByExercise = module
                    .getMaxScoreByExercise() * countStudents;
            Integer maxStudentsScoreByControlQuestion = module
                    .getMaxScoreByControlQuestion() * countStudents;
            Integer maxStudentsScoreByHomeWork = module
                    .getMaxScoreByHomeWork() * countStudents;
            Integer studentsScoreByExercise = 0;
            Integer studentScoreByControlQuestion = 0;
            Integer studentScoreByHomeWork = 0;
            for (ModuleScore moduleScore : module.getModuleScores()) {
                studentsScoreByExercise += moduleScore.getScoreByExercise();
                studentScoreByControlQuestion += moduleScore.getScoreByControlQuestion();
                studentScoreByHomeWork += moduleScore.getScoreByHomeWork();
            }
            String moduleName = module.getName().split("\\.")[0];
            dataset.addValue(getPercentage(maxStudentsScoreByExercise, studentsScoreByExercise),
                    "Баллы за упражнения",
                    moduleName);
            dataset.addValue(getPercentage(maxStudentsScoreByControlQuestion, studentScoreByControlQuestion),
                    "Баллы за контрольные вопросы",
                    moduleName);
            dataset.addValue(getPercentage(maxStudentsScoreByHomeWork, studentScoreByHomeWork),
                    "Баллы за домашнии задания",
                    moduleName);

        }
        return dataset;
    }

    /**
     * Распределение студентов среди сообществ вк
     *
     * @param groups список сообществ
     * @return датасет
     */
    public static PieDataset<String> createDatasetStudentInVkGroups(List<GroupFromVk> groups) {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        groups.forEach(g -> dataset.setValue(g.getName(), g.getStudents().size()));
        return dataset;
    }

    private static Integer getPercentage(Integer maxScore, Integer studentScores) {
        if (maxScore != 0) {
            return studentScores * 100 / maxScore;
        } else {
            return 0;
        }
    }
}
