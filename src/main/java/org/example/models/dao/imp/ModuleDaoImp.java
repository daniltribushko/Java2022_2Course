package org.example.models.dao.imp;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.models.dao.ModuleDao;
import org.example.models.entities.Module;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 22.01.2024
 * <p>
 * Реализация dao модуля курса
 */
public class ModuleDaoImp implements ModuleDao {
    @Override
    public void save(Session session, Module object) {
        Transaction transaction = session.beginTransaction();
        session.persist(object);
        transaction.commit();
    }

    @Override
    public void update(Session session, Module object) {
        Transaction transaction = session.beginTransaction();
        session.merge(object);
        transaction.commit();
    }

    @Override
    public void delete(Session session, Module object) {
        Transaction transaction = session.beginTransaction();
        session.remove(object);
        transaction.commit();
    }

    @Override
    public Optional<Module> findById(Session session, Integer id) {
        return Optional.ofNullable(session.find(Module.class, id));
    }

    @Override
    public List<Module> findAll(Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Module> query = builder.createQuery(Module.class);
        query.select(query.from(Module.class));
        return session.createQuery(query)
                .getResultList();
    }

    @Override
    public Optional<Module> findByName(Session session, String name) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Module> query = builder.createQuery(Module.class);
        Root<Module> root = query.from(Module.class);
        query.select(root).where(builder.equal(root.get("name"), name));
        return Optional.ofNullable(session.createQuery(query).uniqueResult());
    }
}
