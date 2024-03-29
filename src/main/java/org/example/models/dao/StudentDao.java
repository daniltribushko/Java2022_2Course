package org.example.models.dao;

import org.example.models.entities.Student;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 22.01.2024
 * <p>
 * Dao студента
 */
public interface StudentDao extends Dao<Student> {
    /**
     * Получение студента по его полному имени
     *
     * @param session  сессия
     * @param fullName полное имя студента
     * @return сущность студента
     */
    Optional<Student> findByFullName(Session session, String fullName);

    /**
     * Получение списка студентов по их группе
     *
     * @param session сессия
     * @param group   группа
     * @return список студентов
     */
    List<Student> findAllByGroup(Session session, Integer group);
}
