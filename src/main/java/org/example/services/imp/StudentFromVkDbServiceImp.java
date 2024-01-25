package org.example.services.imp;

import org.example.models.dao.StudentFromVkDao;
import org.example.models.dao.imp.StudentFromVkDaoImp;
import org.example.models.entities.StudentFromVk;
import org.example.services.StudentFromVkDbService;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 25.01.2024
 * <p>
 * Реализация сервиса по работе с сущностями студента вк в бд
 */
public class StudentFromVkDbServiceImp implements StudentFromVkDbService {
    private final StudentFromVkDao dao;

    public StudentFromVkDbServiceImp() {
        dao = new StudentFromVkDaoImp();
    }

    @Override
    public void save(Session session, StudentFromVk student) {
        StudentFromVk newStudent = findByFullName(session, student.getName())
                .orElse(null);
        if (newStudent != null) {
            System.out.println("!!!Ошибка!!! Студент " + student.getName() + " уже сохранен");
        } else {
            dao.save(session, student);
        }
    }

    @Override
    public void update(Session session, StudentFromVk student) {
        StudentFromVk newStudent = findByFullName(session, student.getName())
                .orElse(null);
        if (newStudent == null) {
            System.out.println("!!!Ошибка!!! Студент " + student.getName() + " не найден");
        } else {
            dao.update(session, student);
        }
    }

    @Override
    public void delete(Session session, StudentFromVk student) {
        StudentFromVk newStudent = findByFullName(session, student.getName())
                .orElse(null);
        if (newStudent == null) {
            System.out.println("!!!Ошибка!!! Студент " + student.getName() + " не найден");
        } else {
            dao.delete(session, student);
        }
    }

    @Override
    public Optional<StudentFromVk> findById(Session session, Integer id) {
        return dao.findById(session, id);
    }

    @Override
    public List<StudentFromVk> findAll(Session session) {
        return dao.findAll(session);
    }

    @Override
    public Optional<StudentFromVk> findByFullName(Session session, String fullName) {
        return dao.findByFullName(session, fullName);
    }
}
