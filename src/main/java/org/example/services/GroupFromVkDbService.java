package org.example.services;

import org.example.models.entities.GroupFromVk;
import org.hibernate.Session;

import java.util.Optional;

/**
 * @author Tribusshko Danil
 * @since 25.01.2024
 *
 * Сервис для работы с сущностями сообществ вк в бд
 */
public interface GroupFromVkDbService extends DbService<GroupFromVk> {
    Optional<GroupFromVk> findByName(Session session, String name);
}
