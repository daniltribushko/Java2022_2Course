package org.example.models.dao.imp;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.models.dao.TaskDao;
import org.example.models.entities.Task;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 22.01.2024
 * <p>
 * Реализация dao задания курса
 */
public class TaskDaoImp implements TaskDao {
    @Override
    public void save(Session session, Task object) {
        Transaction transaction = session.beginTransaction();
        session.persist(object);
        transaction.commit();
    }

    @Override
    public void update(Session session, Task object) {
        Transaction transaction = session.beginTransaction();
        session.merge(object);
        transaction.commit();
    }

    @Override
    public void delete(Session session, Task object) {
        Transaction transaction = session.beginTransaction();
        session.remove(object);
        transaction.commit();
    }

    @Override
    public Optional<Task> findById(Session session, Integer id) {
        return Optional.ofNullable(session.find(Task.class, id));
    }

    @Override
    public List<Task> findAll(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> query = builder.createQuery(Task.class);
        query.select(query.from(Task.class));
        return session.createQuery(query).getResultList();
    }

    @Override
    public Optional<Task> findByName(Session session, String name) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> query = builder.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);
        query.select(root).where(builder.equal(root.get("name"), name));
        return Optional.ofNullable(session.createQuery(query).uniqueResult());
    }
}
