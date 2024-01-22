package org.example.services;

import org.example.models.entities.ModuleScore;
import org.example.models.entities.Student;
import org.example.models.entities.Task;
import org.example.models.entities.TaskScore;
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
                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
