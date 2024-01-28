package org.example.models.dao.imp;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.models.dao.StudentDao;
import org.example.models.entities.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 22.01.2024
 * <p>
 * Реализация dao студента
 */
public class StudentDaoImp implements StudentDao {
    private static final Class<Student> CLASS = Student.class;

    @Override
    public void save(Session session, Student object) {
        Transaction transaction = session.beginTransaction();
        session.persist(object);
        transaction.commit();
    }

    @Override
    public void update(Session session, Student object) {
        Transaction transaction = session.beginTransaction();
        session.merge(object);
        transaction.commit();
    }

    @Override
    public void delete(Session session, Student object) {
        Transaction transaction = session.beginTransaction();
        session.remove(object);
        transaction.commit();
    }

    @Override
    public Optional<Student> findById(Session session, Integer id) {
        return Optional.ofNullable(session.find(CLASS, id));
    }

    @Override
    public List<Student> findAll(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(CLASS);
        query.select(query.from(CLASS));
        return session.createQuery(query).getResultList();
    }

    @Override
    public Optional<Student> findByFullName(Session session, String fullName) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(CLASS);
        Root<Student> root = query.from(CLASS);
        query.select(root).where(builder.equal(root.get("fullName"), fullName));
        return Optional.ofNullable(session.createQuery(query).uniqueResult());
    }

    @Override
    public List<Student> findAllByGroup(Session session, Integer group) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(CLASS);
        Root<Student> root = query.from(CLASS);
        query.select(root).where(builder.equal(root.get("group"), group));
        return session.createQuery(query).getResultList();
    }
}
