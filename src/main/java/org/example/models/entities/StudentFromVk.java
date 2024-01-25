package org.example.models.entities;

import jakarta.persistence.*;
import org.example.models.enums.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 25.01.2024
 *
 * Сущность студента вк
 */
@Entity
@Table(name = "vk_students")
public class StudentFromVk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fullname", nullable = false)
    private String fullName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "city")
    private String city;

    @Column(name = "vkId", nullable = false)
    private Long vkId;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToMany(mappedBy = "students")
    private List<GroupFromVk> groups;

    public StudentFromVk(String fullName, Long vkId, Gender gender, String city){
        this.fullName = fullName;
        this.vkId = vkId;
        this.gender = gender;
        this.city = city;
        groups = new ArrayList<>();
    }

    public StudentFromVk(){}


    public Long getVkId() {
        return vkId;
    }

    public void setVkId(Long vkId) {
        this.vkId = vkId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<GroupFromVk> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupFromVk> groups) {
        this.groups = groups;
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
        StudentFromVk that = (StudentFromVk) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(fullName, that.fullName) &&
                gender == that.gender &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, gender, city);
    }

    @Override
    public String toString() {
        return "StudentFromVk{" +
                "id=" + id +
                ", name='" + fullName + '\'' +
                ", gender=" + gender +
                ", city='" + city + '\'' +
                '}';
    }
}
