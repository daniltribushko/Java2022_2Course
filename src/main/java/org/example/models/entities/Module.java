package org.example.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 21.01.2024
 * <p>
 * Сущность модуля курса для сохранения в бд
 */
@Entity
@Table(name = "modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "maxScoreByExercise", nullable = false)
    private Integer maxScoreByExercise;

    @Column(name = "maxScoreByControlQuestion", nullable = false)
    private Integer maxScoreByControlQuestion;

    @Column(name = "maxScoreByHomeWork", nullable = false)
    private Integer maxScoreByHomeWork;

    @OneToMany(mappedBy = "module", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ModuleScore> moduleScores;

    public Module(String name,
                  Integer maxScoreByExercise,
                  Integer maxScoreByControlQuestion,
                  Integer maxScoreByHomeWork) {
        this.name = name;
        this.maxScoreByExercise = maxScoreByExercise;
        this.maxScoreByControlQuestion = maxScoreByControlQuestion;
        this.maxScoreByHomeWork = maxScoreByHomeWork;
        moduleScores = new ArrayList<>();
    }

    public Module() {
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

    public Integer getMaxScoreByExercise() {
        return maxScoreByExercise;
    }

    public void setMaxScoreByExercise(Integer maxScoreByExercise) {
        this.maxScoreByExercise = maxScoreByExercise;
    }

    public Integer getMaxScoreByControlQuestion() {
        return maxScoreByControlQuestion;
    }

    public void setMaxScoreByControlQuestion(Integer maxScoreByControlQuestion) {
        this.maxScoreByControlQuestion = maxScoreByControlQuestion;
    }

    public Integer getMaxScoreByHomeWork() {
        return maxScoreByHomeWork;
    }

    public void setMaxScoreByHomeWork(Integer maxScoreByHomeWork) {
        this.maxScoreByHomeWork = maxScoreByHomeWork;
    }

    public void addModuleScore(ModuleScore moduleScore){
        moduleScore.setModule(this);
        moduleScores.add(moduleScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return Objects.equals(id, module.id) &&
                Objects.equals(name, module.name) &&
                Objects.equals(maxScoreByExercise, module.maxScoreByExercise) &&
                Objects.equals(maxScoreByControlQuestion, module.maxScoreByControlQuestion) &&
                Objects.equals(maxScoreByHomeWork, module.maxScoreByHomeWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, maxScoreByExercise, maxScoreByControlQuestion, maxScoreByHomeWork);
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxScoreByExercise=" + maxScoreByExercise +
                ", maxScoreByControlQuestion=" + maxScoreByControlQuestion +
                ", maxScoreByHomeWork=" + maxScoreByHomeWork +
                '}';
    }
}
