package org.example.models.vk;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 24.01.2024
 *
 * Модель сообщества из вк
 */
public class VkGroup {
    /**
     * Идентификатор группы в вк
     */
    private Long id;
    /**
     * Название группы
     */
    private String name;

    /**
     * Список студентов вк, которые состоят в группе
     */
    private List<VkStudent> students;

    public VkGroup(Long id, String name){
        this.id = id;
        this.name = name;
        students = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VkStudent> getStudents() {
        return students;
    }

    public void setStudents(List<VkStudent> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VkGroup vkGroup = (VkGroup) o;
        return Objects.equals(id, vkGroup.id) && Objects.equals(name, vkGroup.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "VkGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
