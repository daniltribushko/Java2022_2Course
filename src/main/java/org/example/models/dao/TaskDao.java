package org.example.models.dao;

import org.example.models.entities.Task;
import org.hibernate.Session;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 22.01.2024
 * <p>
 * Dao задания курса
 */
public interface TaskDao extends Dao<Task> {
    /**
     * Получение сущности задания по его названию
     *
     * @param session сессия
     * @param name    название задания
     * @return сущность задания
     */
    Optional<Task> findByName(Session session, String name);
}
