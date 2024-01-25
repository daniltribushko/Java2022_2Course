package org.example.models.vk;

import org.example.models.enums.Gender;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 24.01.2024
 *
 * Модуль стдуента из вк
 */
public class VkStudent {
    private String name;
    private Gender gender;
    private List<VkGroup> groups;
    private String city;

    public VkStudent(String name, Gender gender){
        this.name = name;
        this.gender = gender;
        groups = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<VkGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<VkGroup> groups) {
        this.groups = groups;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VkStudent vkStudent = (VkStudent) o;
        return Objects.equals(name, vkStudent.name) && gender == vkStudent.gender && Objects.equals(city, vkStudent.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, city);
    }

    @Override
    public String toString() {
        return "VkStudent{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", city='" + city + '\'' +
                '}';
    }
}
