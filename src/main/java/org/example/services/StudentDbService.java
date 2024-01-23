package org.example.services;

import org.example.models.entities.Student;
import org.hibernate.Session;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 23.01.2023
 *
 * Сервис для работы со студентами в бд
 */
public interface StudentDbService extends DbService<Student> {
    Optional<Student> findByFullName(Session session, String fullName);
}
