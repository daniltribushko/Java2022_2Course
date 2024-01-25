package org.example.services;

import org.example.models.entities.StudentFromVk;
import org.hibernate.Session;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 25.01.2024
 * <p>
 * Сервис для работы с сущностями студента из вк в бд
 */
public interface StudentFromVkDbService extends DbService<StudentFromVk> {
    /**
     * Получение студента вк из бд
     *
     * @param session  сессия бд
     * @param fullName полное имя студента
     * @return сушность студента вк из бд
     */
    Optional<StudentFromVk> findByFullName(Session session, String fullName);
}
