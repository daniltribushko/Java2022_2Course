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
public class Task {
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
    private List<TaskScore> scores;

    public Task(String name, TaskType type, Integer maxScore) {
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

    public List<TaskScore> getScores(){
        return scores;
    }

    public void setScores(List<TaskScore> scores){
        this.scores = scores;
    }

    public void addScore(TaskScore score){
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
        Task task = (Task) o;
        return Objects.equals(name, task.name) &&
                type == task.type &&
                Objects.equals(maxScore, task.maxScore);
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
