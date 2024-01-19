package org.example.models.csv;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 18.01.2024
 * <p>
 * Модель модуля курса
 */
public class Module {
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
    private List<Task> tasks;

    /**
     * Список баллов студентов
     */
    private List<ModuleScore> scores;

    public Module(String name,
                  Integer maxScoreByHomework,
                  Integer maxScoreByControlQuestion,
                  Integer maxScoreByExercise,
                  List<Task> tasks,
                  List<ModuleScore> scores){
        this.name = name;
        this.maxScoreByHomework = maxScoreByHomework;
        this.maxScoreByControlQuestion = maxScoreByControlQuestion;
        this.maxScoreByExercise = maxScoreByExercise;
        this.tasks = tasks;
        this.scores = scores;
    }

    public Module(String name,
                  Integer maxScoreByHomework,
                  Integer maxScoreByControlQuestion,
                  Integer maxScoreByExercise,
                  List<Task> tasks){
        this.name = name;
        this.maxScoreByHomework = maxScoreByHomework;
        this.maxScoreByControlQuestion = maxScoreByControlQuestion;
        this.maxScoreByExercise = maxScoreByExercise;
        this.tasks = tasks;
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

    public List<Task> getTasks(){
        return tasks;
    }

    public void setTasks(List<Task> tasks){
        this.tasks = tasks;
    }

    public List<ModuleScore> getScores(){
        return scores;
    }

    public void setScores(List<ModuleScore> scores){
        this.scores = scores;
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (o == null || o.getClass() != getClass()){
            return false;
        }
        Module module = (Module) o;
        return Objects.equals(name, module.name) &&
                Objects.equals(maxScoreByControlQuestion, module.maxScoreByControlQuestion) &&
                Objects.equals(maxScoreByExercise, module.maxScoreByExercise) &&
                Objects.equals(maxScoreByHomework, module.maxScoreByHomework) &&
                Objects.equals(tasks, module.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, maxScoreByHomework, maxScoreByControlQuestion, maxScoreByExercise, tasks);
    }

    @Override
    public String toString(){
        return String.format("Module={name=%s, maxScoreByHomeWork=%d, " +
                "maxScoreByControlQuestion=%d, maxScoreByExercise=%d, tasks=%s}",
                name, maxScoreByHomework, maxScoreByControlQuestion, maxScoreByExercise, tasks.toString());
    }
}
