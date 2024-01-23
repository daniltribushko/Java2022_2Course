package org.example.models.csv;

import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 18.01.2024
 * <p>
 * Модель успеваемости студента за задание
 */
public class TaskScoreCsv {
    /**
     * Студент курса
     */
    private final StudentCsv studentCsv;

    /**
     * Задание
     */
    private final TaskCsv taskCsv;

    /**
     * Количество набранных баллов
     */
    private Integer score;

    public TaskScoreCsv(StudentCsv studentCsv, TaskCsv taskCsv, Integer score) {
        this.studentCsv = studentCsv;
        this.taskCsv = taskCsv;
        this.score = score;
    }

    public StudentCsv getStudent() {
        return studentCsv;
    }

    public TaskCsv getTask() {
        return taskCsv;
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
        TaskScoreCsv taskScore = (TaskScoreCsv) o;
        return Objects.equals(studentCsv, taskScore.studentCsv) &&
                Objects.equals(taskCsv, taskScore.taskCsv) &&
                Objects.equals(score, taskScore.score);
    }

    @Override
    public int hashCode(){
        return Objects.hash(studentCsv, taskCsv, score);
    }

    @Override
    public String toString(){
        return String.format("TaskScore={student=%s, task=%s, score=%d}",
                studentCsv.toString(), taskCsv.toString(), score);
    }
}
