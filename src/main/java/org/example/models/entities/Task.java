package org.example.models.entities;

import jakarta.persistence.*;
import org.example.models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 21.01.2024
 * <p>
 * Сущность задания для сохранения в бд
 */
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Название задания
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Тип задания
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TaskType type;

    /**
     * Максимальное количество баллов за задание
     */
    @Column(name = "score", nullable = false)
    private Integer maxScore;

    /**
     * Список баллов студентов за задание
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "task")
    private List<TaskScore> taskScores;

    public Task(String name, TaskType type, Integer maxScore) {
        this.name = name;
        this.type = type;
        this.maxScore = maxScore;
        taskScores = new ArrayList<>();
    }

    public Task() {
    }

    public Integer getId() {
        return id;
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

    public List<TaskScore> getTaskScores() {
        return taskScores;
    }

    public void setTaskScores(List<TaskScore> taskScores) {
        this.taskScores = taskScores;
    }

    /**
     * Добавление баллов студентов в список
     *
     * @param taskScore баллы студента за задание
     */
    public void addTaskScore(TaskScore taskScore) {
        taskScore.setTask(this);
        taskScores.add(taskScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(name, task.name) &&
                type == task.type &&
                Objects.equals(maxScore, task.maxScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, maxScore);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", score=" + maxScore +
                '}';
    }
}
