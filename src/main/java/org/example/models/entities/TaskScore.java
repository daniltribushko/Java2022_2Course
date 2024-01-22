package org.example.models.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "taskScores")
public class TaskScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "score")
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "task")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    public TaskScore(Integer score){
        this.score = score;
    }

    public TaskScore(){}

    public Integer getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskScore taskScore = (TaskScore) o;
        return Objects.equals(id, taskScore.id) &&
                Objects.equals(score, taskScore.score) &&
                Objects.equals(task, taskScore.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, task);
    }

    @Override
    public String toString() {
        return "TaskScore{" +
                "id=" + id +
                ", score=" + score +
                ", task=" + task +
                '}';
    }
}
