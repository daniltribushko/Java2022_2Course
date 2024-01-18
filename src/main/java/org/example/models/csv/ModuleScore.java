package org.example.models.csv;

import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 18.01.2024
 * <p>
 * Модель успеваемости студента по модулю
 */
public class ModuleScore {
    /**
     * Модуль
     */
    private final Module module;

    /**
     * Студент
     */
    private final Student student;

    /**
     * Количество баллов за домашнии задания
     */
    private Integer scoreByHomeWork;

    /**
     * Количество баллов за контрольные вопросы
     */
    private Integer scoreByControlQuestion;

    /**
     * Количество баллов за упражнения
     */
    private Integer scoreByExercise;

    public ModuleScore(Module module,
                       Student student,
                       Integer scoreByHomeWork,
                       Integer scoreByControlQuestion,
                       Integer scoreByExercise) {
        this.module = module;
        this.student = student;
        this.scoreByHomeWork = scoreByHomeWork;
        this.scoreByControlQuestion = scoreByControlQuestion;
        this.scoreByExercise = scoreByExercise;
    }

    public Module getModule() {
        return module;
    }

    public Student getStudent() {
        return student;
    }

    public Integer getScoreByHomeWork() {
        return scoreByHomeWork;
    }

    public void setScoreByHomeWork(Integer scoreByHomeWork) {
        this.scoreByHomeWork = scoreByHomeWork;
    }

    public Integer getScoreByControlQuestion() {
        return scoreByControlQuestion;
    }

    public void setScoreByControlQuestion(Integer scoreByControlQuestion) {
        this.scoreByControlQuestion = scoreByControlQuestion;
    }

    public Integer getScoreByExercise() {
        return scoreByExercise;
    }

    public void setScoreByExercise(Integer scoreByExercise) {
        this.scoreByExercise = scoreByExercise;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        ModuleScore moduleScore = (ModuleScore) o;
        return Objects.equals(module, moduleScore.module) &&
                Objects.equals(student, moduleScore.student) &&
                Objects.equals(scoreByExercise, moduleScore.scoreByExercise) &&
                Objects.equals(scoreByControlQuestion, moduleScore.scoreByControlQuestion) &&
                Objects.equals(scoreByHomeWork, moduleScore.scoreByHomeWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(module, student, scoreByExercise, scoreByControlQuestion, scoreByHomeWork);
    }

    @Override
    public String toString() {
        return String.format("ModuleScore={module=%s, student=%s, scoreByExercise=%d," +
                        " scoreByControlQuestion=%d, scoreByHomeWork=%d}", module.toString(), student.toString(),
                scoreByExercise, scoreByControlQuestion, scoreByHomeWork);
    }
}
