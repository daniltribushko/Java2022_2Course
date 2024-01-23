package org.example.models.csv;

import org.example.models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 18.01.2024
 * <p>
 * Модель задания в курсе
 */
public class TaskCsv {
    /**
     * Название задания
     */
    private String name;

    /**
     * Тип задания
     */
    private TaskType type;

    /**
     * Максимальное количество баллов за задание
     */
    private Integer maxScore;

    /**
     * Список баллов студентов
     */
    private List<TaskScoreCsv> scores;

    public TaskCsv(String name, TaskType type, Integer maxScore) {
        this.name = name;
        this.type = type;
        this.maxScore = maxScore;
        scores = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public List<TaskScoreCsv> getScores(){
        return scores;
    }

    public void setScores(List<TaskScoreCsv> scores){
        this.scores = scores;
    }

    public void addScore(TaskScoreCsv score){
        scores.add(score);
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskCsv taskCsv = (TaskCsv) o;
        return Objects.equals(name, taskCsv.name) &&
                type == taskCsv.type &&
                Objects.equals(maxScore, taskCsv.maxScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, maxScore);
    }

    @Override
    public String toString(){
        return String.format("Task={name=%s, type=%s, maxScore=%d", name, type, maxScore);
    }
}
