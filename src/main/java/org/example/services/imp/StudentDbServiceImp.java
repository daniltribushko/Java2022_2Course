package org.example.services.imp;

import org.example.models.dao.StudentDao;
import org.example.models.dao.imp.StudentDaoImp;
import org.example.models.entities.Student;
import org.example.services.StudentDbService;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 24.01.2024
 *
 * Реализация сервиса по работе с студентами в бд
 */
public class StudentDbServiceImp implements StudentDbService {
    private final StudentDao dao;
    public StudentDbServiceImp(){
        dao = new StudentDaoImp();
    }
    @Override
    public void save(Session session, Student student) {
        Student newStudent = dao.findByFullName(session, student.getFullName())
                .orElse(null);
        if (newStudent != null){
            System.out.println("!!!Ошибка!!! Студент " + newStudent.getFullName() + " уже сохранен");
        } else {
            dao.save(session, student);
        }
    }

    @Override
    public void update(Session session, Student student) {
        Student newStudent = dao.findById(session, student.getId())
                .orElse(null);
        if (newStudent != null){
            dao.update(session, student);
        } else {
            System.out.println("!!!Ошибка!!! Студент " + student.getFullName() + " не найден");
        }
    }

    @Override
    public void delete(Session session, Student student) {
        Student newStudent = dao.findById(session, student.getId())
                .orElse(null);
        if (newStudent != null){
            dao.delete(session, student);
        } else {
            System.out.println("!!!Ошибка!!! Студент " + student.getFullName() + " не найден");
        }
    }

    @Override
    public Optional<Student> findById(Session session, Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll(Session session) {
        return dao.findAll(session);
    }

    @Override
    public Optional<Student> findByFullName(Session session, String fullName) {
        return dao.findByFullName(session, fullName);
    }

    @Override
    public List<Student> findAllByGroup(Session session, Integer group) {
        return dao.findAllByGroup(session, group);
    }
}
