package org.example.models.entities;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 21.01.2024
 * <p>
 * Сущность баллов студента за курс
 */
@Entity
@Table(name = "moduleScores")
public class ModuleScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "scoreByExercise", nullable = false)
    private Integer scoreByExercise;

    @Column(name = "scoreByControlQuestion", nullable = false)
    private Integer scoreByControlQuestion;

    @Column(name = "scoreByHomeWork", nullable = false)
    private Integer scoreByHomeWork;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "module")
    private Module module;

    public ModuleScore(Integer scoreByExercise, Integer scoreByControlQuestion, Integer scoreByHomeWork) {
        this.scoreByExercise = scoreByExercise;
        this.scoreByControlQuestion = scoreByControlQuestion;
        this.scoreByHomeWork = scoreByHomeWork;
    }

    public ModuleScore(){}
    public Integer getId() {
        return id;
    }

    public Integer getScoreByExercise() {
        return scoreByExercise;
    }

    public void setScoreByExercise(Integer scoreByExercise) {
        this.scoreByExercise = scoreByExercise;
    }

    public Integer getScoreByControlQuestion() {
        return scoreByControlQuestion;
    }

    public void setScoreByControlQuestion(Integer scoreByControlQuestion) {
        this.scoreByControlQuestion = scoreByControlQuestion;
    }

    public Integer getScoreByHomeWork() {
        return scoreByHomeWork;
    }

    public void setScoreByHomeWork(Integer scoreByHomeWork) {
        this.scoreByHomeWork = scoreByHomeWork;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModuleScore that = (ModuleScore) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(scoreByExercise, that.scoreByExercise) &&
                Objects.equals(scoreByControlQuestion, that.scoreByControlQuestion) &&
                Objects.equals(scoreByHomeWork, that.scoreByHomeWork) &&
                Objects.equals(student, that.student) &&
                Objects.equals(module, that.module);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scoreByExercise, scoreByControlQuestion, scoreByHomeWork, student, module);
    }

    @Override
    public String toString() {
        return "ModuleScore{" +
                "id=" + id +
                ", scoreByExercise=" + scoreByExercise +
                ", scoreByControlQuestion=" + scoreByControlQuestion +
                ", scoreByHomeWork=" + scoreByHomeWork +
                ", student=" + student +
                ", module=" + module +
                '}';
    }
}
