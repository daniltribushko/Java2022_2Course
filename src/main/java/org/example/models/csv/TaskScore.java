package org.example.models.csv;

import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 18.01.2024
 * <p>
 * Модель успеваемости студента за задание
 */
public class TaskScore {
    /**
     * Студент курса
     */
    private final Student student;

    /**
     * Задание
     */
    private final Task task;

    /**
     * Количество набранных баллов
     */
    private Integer score;

    public TaskScore(Student student, Task task, Integer score) {
        this.student = student;
        this.task = task;
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public Task getTask() {
        return task;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        TaskScore taskScore = (TaskScore) o;
        return Objects.equals(student, taskScore.student) &&
                Objects.equals(task, taskScore.task) &&
                Objects.equals(score, taskScore.score);
    }

    @Override
    public int hashCode(){
        return Objects.hash(student, task, score);
    }

    @Override
    public String toString(){
        return String.format("TaskScore={student=%s, task=%s, score=%d}",
                student.toString(), task.toString(), score);
    }
}
