package org.example.models.dao;

import org.example.models.entities.Module;
import org.hibernate.Session;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 22.01.2024
 * <p>
 * Dao сущности модуля курса
 */
public interface ModuleDao extends Dao<Module> {
    /**
     * Получение модули по его названию
     *
     * @param session сессия
     * @param name    название модуля
     * @return сущность модуля
     */
    Optional<Module> findByName(Session session, String name);
}
