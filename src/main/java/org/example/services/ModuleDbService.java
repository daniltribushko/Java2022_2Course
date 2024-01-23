package org.example.services;

import org.example.models.entities.Module;
import org.hibernate.Session;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 23.01.2024
 *
 * Сервис для работы с модулями курса в бд
 */
public interface ModuleDbService extends DbService<Module> {
    Optional<Module> findByName(Session session, String name);
}
