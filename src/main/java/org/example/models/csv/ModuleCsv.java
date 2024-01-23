package org.example.models.csv;

import java.util.*;

/**
 * @author Tribushko Danil
 * @since 18.01.2024
 * <p>
 * Модель модуля курса
 */
public class ModuleCsv {
    /**
     * Название модуля
     */
    private String name;

    /**
     * Максимальное количество баллов за домашнии задания
     */
    private Integer maxScoreByHomework;

    /**
     * Масимальное количество баллов за контрольные вопросы
     */
    private Integer maxScoreByControlQuestion;

    /**
     * Максимальное количество баллов за упражнения
     */
    private Integer maxScoreByExercise;

    /**
     * Список заданий
     */
    private List<TaskCsv> taskCsvs;

    /**
     * Список баллов студентов
     */
    private List<ModuleScoreCsv> scores;

    public ModuleCsv(String name,
                     Integer maxScoreByHomework,
                     Integer maxScoreByControlQuestion,
                     Integer maxScoreByExercise,
                     List<TaskCsv> taskCsvs){
        this.name = name;
        this.maxScoreByHomework = maxScoreByHomework;
        this.maxScoreByControlQuestion = maxScoreByControlQuestion;
        this.maxScoreByExercise = maxScoreByExercise;
        this.taskCsvs = taskCsvs;
        scores = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Integer getMaxScoreByHomework(){
        return maxScoreByHomework;
    }

    public void setMaxScoreByHomework(Integer maxScoreByHomework){
        this.maxScoreByHomework = maxScoreByHomework;
    }

    public Integer getMaxScoreByControlQuestion(){
        return maxScoreByControlQuestion;
    }

    public void setMaxScoreByControlQuestion(Integer maxScoreByControlQuestion){
        this.maxScoreByControlQuestion = maxScoreByControlQuestion;
    }

    public Integer getMaxScoreByExercise(){
        return maxScoreByExercise;
    }

    public void setMaxScoreByExercise(Integer maxScoreByExercise){
        this.maxScoreByExercise = maxScoreByExercise;
    }

    public List<TaskCsv> getTasks(){
        return taskCsvs;
    }

    public void setTasks(List<TaskCsv> taskCsvs){
        this.taskCsvs = taskCsvs;
    }

    public List<ModuleScoreCsv> getScores(){
        return scores;
    }

    public void setScores(List<ModuleScoreCsv> scores){
        this.scores = scores;
    }

    public void addScore(ModuleScoreCsv score){
        scores.add(score);
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (o == null || o.getClass() != getClass()){
            return false;
        }
        ModuleCsv moduleCsv = (ModuleCsv) o;
        return Objects.equals(name, moduleCsv.name) &&
                Objects.equals(maxScoreByControlQuestion, moduleCsv.maxScoreByControlQuestion) &&
                Objects.equals(maxScoreByExercise, moduleCsv.maxScoreByExercise) &&
                Objects.equals(maxScoreByHomework, moduleCsv.maxScoreByHomework) &&
                Objects.equals(taskCsvs, moduleCsv.taskCsvs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxScoreByHomework, maxScoreByControlQuestion, maxScoreByExercise, taskCsvs);
    }

    @Override
    public String toString(){
        return String.format("Module={name=%s, maxScoreByHomeWork=%d, " +
                "maxScoreByControlQuestion=%d, maxScoreByExercise=%d}",
                name, maxScoreByHomework, maxScoreByControlQuestion, maxScoreByExercise);
    }
}
