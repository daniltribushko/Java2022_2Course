package org.example.models.csv;

import java.util.Objects;

/**
 * @author Tribushko Danil
 * @since 18.01.2024
 * <p>
 * Модель студента курса
 */
public class StudentCsv {
    /**
     * Имя и фамилия студента курса
     */
    private String fullName;

    /**
     * Идентификатор студента на uLearn
     */
    private String uLearnId;

    /**
     * Номер группы
     */
    private Integer group;

    public StudentCsv(String fullName, String uLearnId, Integer group){
        this.fullName = fullName;
        this.uLearnId = uLearnId;
        this.group = group;
    }

    public String getFullName(){
        return fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getULearnId(String uLearnId){
        return uLearnId;
    }

    public void setULearnId(String uLearnId){
        this.uLearnId = uLearnId;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        }
        if (o == null || o.getClass() != getClass()){
            return false;
        }
        StudentCsv studentCsv = (StudentCsv) o;
        return Objects.equals(fullName, studentCsv.fullName) && Objects.equals(uLearnId, studentCsv.uLearnId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(fullName, uLearnId);
    }

    @Override
    public String toString(){
        return String.format("Student={fullName=%s, uLearnId=%s}", fullName, uLearnId);
    }
}
