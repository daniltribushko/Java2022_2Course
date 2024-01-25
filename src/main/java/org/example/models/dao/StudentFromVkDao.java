package org.example.models.dao;

import org.example.models.entities.StudentFromVk;
import org.hibernate.Session;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 25.01.2024
 * <p>
 * Dao студента из вк
 */
public interface StudentFromVkDao extends Dao<StudentFromVk>{
    /**
     * Поиск студента вк по полному имени
     *
     * @param session сессия бд
     * @param fullName полное имя студента
     * @return студент вк
     */
    Optional<StudentFromVk> findByFullName(Session session, String fullName);
}
