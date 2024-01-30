package org.example.services.threads;

import org.hibernate.SessionFactory;

/**
 * @author Tribushko Danil
 * @since 30.01.2024
 *
 * Абстрактный класс потока для отрисовки графиков в отдельном потоке
 */
public abstract class GraphicsDrawerThread {
    protected final SessionFactory factory;
    protected GraphicsDrawerThread(SessionFactory factory){
        this.factory = factory;
    }
}
