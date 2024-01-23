package org.example.models.csv;

import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 18.01.2024
 * <p>
 * Модель успеваемости студента по модулю
 */
public class ModuleScoreCsv {
    /**
     * Модуль
     */
    private final ModuleCsv moduleCsv;

    /**
     * Студент
     */
    private final StudentCsv studentCsv;

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

    public ModuleScoreCsv(ModuleCsv moduleCsv,
                          StudentCsv studentCsv,
                          Integer scoreByHomeWork,
                          Integer scoreByControlQuestion,
                          Integer scoreByExercise) {
        this.moduleCsv = moduleCsv;
        this.studentCsv = studentCsv;
        this.scoreByHomeWork = scoreByHomeWork;
        this.scoreByControlQuestion = scoreByControlQuestion;
        this.scoreByExercise = scoreByExercise;
    }

    public ModuleCsv getModule() {
        return moduleCsv;
    }

    public StudentCsv getStudent() {
        return studentCsv;
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
        ModuleScoreCsv moduleScoreCsv = (ModuleScoreCsv) o;
        return Objects.equals(moduleCsv, moduleScoreCsv.moduleCsv) &&
                Objects.equals(studentCsv, moduleScoreCsv.studentCsv) &&
                Objects.equals(scoreByExercise, moduleScoreCsv.scoreByExercise) &&
                Objects.equals(scoreByControlQuestion, moduleScoreCsv.scoreByControlQuestion) &&
                Objects.equals(scoreByHomeWork, moduleScoreCsv.scoreByHomeWork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleCsv, studentCsv, scoreByExercise, scoreByControlQuestion, scoreByHomeWork);
    }

    @Override
    public String toString() {
        return String.format("ModuleScore={module=%s, student=%s, scoreByExercise=%d," +
                        " scoreByControlQuestion=%d, scoreByHomeWork=%d}", moduleCsv.toString(), studentCsv.toString(),
                scoreByExercise, scoreByControlQuestion, scoreByHomeWork);
    }
}
