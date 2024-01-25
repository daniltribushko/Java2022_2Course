package org.example.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vk_groups")
public class GroupFromVk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "vkId", nullable = false)
    private String vkId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "vk_groups_students",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_vk_id"))
    private List<StudentFromVk> students;

    public GroupFromVk(String name, String vkId){
        this.name = name;
        this.vkId = vkId;
        students = new ArrayList<>();
    }

    public GroupFromVk(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVkId() {
        return vkId;
    }

    public void setVkId(String vkId) {
        this.vkId = vkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentFromVk> getStudents() {
        return students;
    }

    public void setStudents(List<StudentFromVk> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupFromVk that = (GroupFromVk) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    public void addStudent(StudentFromVk student){
        List<GroupFromVk> studentGroups = student.getGroups();
        studentGroups.add(this);
        student.setGroups(studentGroups);
        students.add(student);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "GroupFromVk{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
