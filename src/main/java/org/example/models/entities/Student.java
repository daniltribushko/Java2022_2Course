package org.example.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 21.01.2024
 * <p>
 * Сущность студента для созранения в бд
 */
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Имя и фамилия студента курса
     */
    @Column(name = "fullName", nullable = false)
    private String fullName;

    /**
     * Идентификатор студента на uLearn
     */
    @Column(name = "uLearnId", nullable = false)
    private String uLearnId;

    /**
     * Номер группы студента
     */
    @Column(name = "groupNumber", nullable = false)
    private Integer group;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "student")
    private StudentFromVk studentVk;

    /**
     * Список баллов студента за задания
     */
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TaskScore> taskScores;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ModuleScore> moduleScores;

    public Student(String fullName, String uLearnId, Integer group) {
        this.fullName = fullName;
        this.uLearnId = uLearnId;
        this.group = group;
        taskScores = new ArrayList<>();
        moduleScores = new ArrayList<>();
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getULearnId() {
        return uLearnId;
    }

    public void setULearnId(String uLearnId) {
        this.uLearnId = uLearnId;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public List<TaskScore> getTaskScores() {
        return taskScores;
    }

    public void setTaskScores(List<TaskScore> taskScores) {
        this.taskScores = taskScores;
    }

    /**
     * Добавление баллов студента за задание в список
     *
     * @param taskScore баллы студента за задание
     */
    public void addTaskScore(TaskScore taskScore) {
        taskScore.setStudent(this);
        taskScores.add(taskScore);
    }

    /**
     * Добавление баллов студента за модуль в список
     *
     * @param moduleScore баллы студента за модуль
     */
    public void addModuleScore(ModuleScore moduleScore){
        moduleScore.setStudent(this);
        moduleScores.add(moduleScore);
    }

    public List<ModuleScore> getModuleScores() {
        return moduleScores;
    }

    public void setModuleScores(List<ModuleScore> moduleScores) {
        this.moduleScores = moduleScores;
    }

    public StudentFromVk getStudentVk() {
        return studentVk;
    }

    public void setStudentVk(StudentFromVk studentVk) {
        this.studentVk = studentVk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) &&
                Objects.equals(fullName, student.fullName) &&
                Objects.equals(uLearnId, student.uLearnId) &&
                Objects.equals(group, student.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, uLearnId, group);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", uLearnId='" + uLearnId + '\'' +
                ", group=" + group +
                '}';
    }
}
