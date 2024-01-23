package org.example.models.dao.imp;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.models.dao.Dao;
import org.example.models.entities.ModuleScore;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 22.01.2024
 * <p>
 * Реализация dao успеваимости студента по модуля за курс
 */
public class ModuleScoreDaoImp implements Dao<ModuleScore> {
    private static final Class<ModuleScore> CLASS = ModuleScore.class;
    @Override
    public void save(Session session, ModuleScore object) {
        Transaction transaction = session.beginTransaction();
        session.persist(object);
        transaction.commit();
    }

    @Override
    public void update(Session session, ModuleScore object) {
        Transaction transaction = session.beginTransaction();
        session.merge(object);
        transaction.commit();
    }

    @Override
    public void delete(Session session, ModuleScore object) {
        Transaction transaction = session.beginTransaction();
        session.remove(object);
        transaction.commit();
    }

    @Override
    public Optional<ModuleScore> findById(Session session, Integer id) {
        return Optional.ofNullable(session.find(CLASS, id));
    }

    @Override
    public List<ModuleScore> findAll(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ModuleScore> query = builder.createQuery(CLASS);
        query.select(query.from(CLASS));
        return session.createQuery(query).getResultList();
    }
}
