package org.example.models.dao;

import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * @param <T> класс entity
 * @author Tribushko Danil
 * @since 21.01.2024
 * <p>
 * Интерфейс dao для работы с бд
 */
public interface Dao<T> {
    /**
     * Сохранение сущности в бд
     *
     * @param session сессия бд
     * @param object  сущность для сохранения
     */
    void save(Session session, T object);

    /**
     * Обновление сущности в бд
     *
     * @param session сессия бд
     * @param object  сущность для обновления
     */
    void update(Session session, T object);

    /**
     * Удаление сущности из бд
     *
     * @param session сессия бд
     * @param object  сущность для удаления
     */
    void delete(Session session, T object);

    /**
     * Получение сущности по идентификатору
     *
     * @param session сессия бд
     * @param id      идентификатор сущности
     * @return сущность
     */
    Optional<T> findById(Session session, Integer id);

    /**
     * Получение всех сущностей из бд
     *
     * @param session сессия бд
     * @return список сущностей
     */
    List<T> findAll(Session session);
}
