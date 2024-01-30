package org.example.services.threads;

import org.example.services.ModuleDbService;
import org.example.services.imp.ModuleDbServiceImp;
import org.example.services.utils.graphics.GraphicsDrawer;
import org.example.services.utils.graphics.ModuleScoresDrawer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

/**
 * @author Tribushko Danil
 * @since 30.01.2024
 *
 * Поток для отрисовки графика, содержащего динамику баллов за упражнения,
 * домашнии задания, контрольные вопросы среди модулей
 */
public class ModuleScoresThread extends GraphicsDrawerThread implements Runnable{
    private final Session session;
    public ModuleScoresThread(SessionFactory factory) {
        super(factory);
        session = factory.openSession();
    }

    @Override
    public void run() {
        ModuleDbService moduleDbService = new ModuleDbServiceImp();
        try {
            GraphicsDrawer graphicsDrawer = new ModuleScoresDrawer("moduleScores",
                    moduleDbService.findAll(session));
            graphicsDrawer.drawGraphic();
        } catch (IOException e){
            System.out.println("!!!Ошибка!!! " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
