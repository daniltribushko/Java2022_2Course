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
    private Long vkId;
    private String name;
    private Gender gender;
    private String city;

    public VkStudent(Long vkId, String name, Gender gender){
        this.vkId = vkId;
        this.name = name;
        this.gender = gender;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getVkId() {
        return vkId;
    }

    public void setVkId(Long vkId) {
        this.vkId = vkId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VkStudent vkStudent = (VkStudent) o;
        return Objects.equals(vkId, vkStudent.vkId) && Objects.equals(name, vkStudent.name) && gender == vkStudent.gender && Objects.equals(city, vkStudent.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vkId, name, gender, city);
    }

    @Override
    public String toString() {
        return "VkStudent{" +
                "vkId=" + vkId +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", city='" + city + '\'' +
                '}';
    }
}
