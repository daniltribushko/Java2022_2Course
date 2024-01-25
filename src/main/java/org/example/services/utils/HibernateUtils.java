package org.example.services.utils;

import org.example.models.entities.*;
import org.example.models.entities.Module;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Tribushko Danil
 * @since 21.01.2024
 * <p>
 * Класс для работы с Hibernate
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory;

    private HibernateUtils() {
    }

    /**
     * Получение фабрики сессий
     *
     * @return фабрику сессии
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Module.class);
                configuration.addAnnotatedClass(ModuleScore.class);
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(TaskScore.class);
                configuration.addAnnotatedClass(Task.class);
                configuration.addAnnotatedClass(StudentFromVk.class);
                configuration.addAnnotatedClass(GroupFromVk.class);
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
