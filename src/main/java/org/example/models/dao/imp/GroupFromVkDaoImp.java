package org.example.models.dao.imp;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.models.dao.GroupFromVkDao;
import org.example.models.entities.GroupFromVk;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 25.01.2024
 * <p>
 * Реализация dao студента вк
 */
public class GroupFromVkDaoImp implements GroupFromVkDao {
    private static final Class<GroupFromVk> CLASS = GroupFromVk.class;

    @Override
    public void save(Session session, GroupFromVk group) {
        Transaction transaction = session.beginTransaction();
        session.persist(group);
        transaction.commit();
    }

    @Override
    public void update(Session session, GroupFromVk group) {
        Transaction transaction = session.beginTransaction();
        session.merge(group);
        transaction.commit();
    }

    @Override
    public void delete(Session session, GroupFromVk group) {
        Transaction transaction = session.beginTransaction();
        session.remove(group);
        transaction.commit();
    }

    @Override
    public Optional<GroupFromVk> findById(Session session, Integer id) {
        return Optional.ofNullable(session.find(CLASS, id));
    }

    @Override
    public List<GroupFromVk> findAll(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GroupFromVk> query = builder.createQuery(CLASS);
        query.select(query.from(CLASS));
        return session.createQuery(query).getResultList();
    }

    @Override
    public Optional<GroupFromVk> findByName(Session session, String name) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<GroupFromVk> query = builder.createQuery(CLASS);
        Root<GroupFromVk> root = query.from(CLASS);
        query.select(root).where(builder.equal(root.get("name"), name));
        return Optional.ofNullable(session.createQuery(query).uniqueResult());
    }
}
