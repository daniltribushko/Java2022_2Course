package org.example.models.dao;

import org.example.models.entities.GroupFromVk;
import org.hibernate.Session;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 25.01.2024
 * <p>
 * Сообщество из вк
 */
public interface GroupFromVkDao extends Dao<GroupFromVk> {
    /**
     * Получение сообщества из бд по его названию
     *
     * @param session сессия бд
     * @param name название сообщества
     * @return сообщество из вк
     */
    Optional<GroupFromVk> findByName(Session session, String name);
}
