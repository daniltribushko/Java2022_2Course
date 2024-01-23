package org.example.services;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * @param <T> Класс сущности для работы
 * @author Tribushko Danil
 * @since 22.01.2024
 * <p>
 * Сервис для работы с бд
 */
public interface DbService<T> {
    /**
     * Сохранение сущности в бд
     *
     * @param session сессия
     * @param object  сущность для сохранения
     */
    void save(Session session, T object);

    /**
     * Обновление сущности в бд
     *
     * @param session сессия
     * @param object  сущность для обновления
     */
    void update(Session session, T object);

    /**
     * Удаление сущности из бд
     *
     * @param session сессия
     * @param object  сущность для удаления
     */
    void delete(Session session, T object);

    /**
     * Получение сущности по идентификатору
     *
     * @param session сессия
     * @param id      идентификатор сущности
     * @return сущность
     */
    Optional<T> findById(Session session, Integer id);

    /**
     * Получение списка сущностей
     *
     * @param session сессия
     * @return список сущностей
     */
    List<T> findAll(Session session);
}
