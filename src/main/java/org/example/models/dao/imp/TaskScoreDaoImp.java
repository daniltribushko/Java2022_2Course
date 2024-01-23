package org.example.models.dao.imp;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.models.dao.Dao;
import org.example.models.entities.TaskScore;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 22.01.2024
 * <p>
 * Реализация dao успеваемости студента за задания
 */
public class TaskScoreDaoImp implements Dao<TaskScore> {
    @Override
    public void save(Session session, TaskScore object) {
        Transaction transaction = session.beginTransaction();
        session.persist(object);
        transaction.commit();
    }

    @Override
    public void update(Session session, TaskScore object) {
        Transaction transaction = session.beginTransaction();
        session.merge(object);
        transaction.commit();
    }

    @Override
    public void delete(Session session, TaskScore object) {
        Transaction transaction = session.beginTransaction();
        session.remove(object);
        transaction.commit();
    }

    @Override
    public Optional<TaskScore> findById(Session session, Integer id) {
        return Optional.ofNullable(session.find(TaskScore.class, id));
    }

    @Override
    public List<TaskScore> findAll(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<TaskScore> query = builder.createQuery(TaskScore.class);
        query.select(query.from(TaskScore.class));
        return session.createQuery(query)
                .getResultList();
    }
}
