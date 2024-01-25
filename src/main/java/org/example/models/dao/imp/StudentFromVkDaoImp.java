package org.example.models.dao.imp;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.models.dao.StudentFromVkDao;
import org.example.models.entities.StudentFromVk;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 25.01.2024
 *
 * Реализация dao студента из вк
 */
public class StudentFromVkDaoImp implements StudentFromVkDao {
    private static final Class<StudentFromVk> CLASS = StudentFromVk.class;
    @Override
    public void save(Session session, StudentFromVk studentFromVk) {
        Transaction transaction = session.beginTransaction();
        session.persist(studentFromVk);
        transaction.commit();
    }

    @Override
    public void update(Session session, StudentFromVk studentFromVk) {
        Transaction transaction = session.beginTransaction();
        session.merge(studentFromVk);
        transaction.commit();
    }

    @Override
    public void delete(Session session, StudentFromVk studentFromVk) {
        Transaction transaction = session.beginTransaction();
        session.remove(studentFromVk);
        transaction.commit();
    }

    @Override
    public Optional<StudentFromVk> findById(Session session, Integer id) {
        return Optional.ofNullable(session.find(CLASS, id));
    }

    @Override
    public List<StudentFromVk> findAll(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<StudentFromVk> query = builder.createQuery(CLASS);
        query.select(query.from(CLASS));
        return session.createQuery(query).getResultList();
    }

    @Override
    public Optional<StudentFromVk> findByFullName(Session session, String fullName) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<StudentFromVk> query = builder.createQuery(CLASS);
        Root<StudentFromVk> root = query.from(CLASS);
        query.select(root).where(builder.equal(root.get("fullName"), fullName));
        return Optional.ofNullable(session.createQuery(query).uniqueResult());
    }
}
