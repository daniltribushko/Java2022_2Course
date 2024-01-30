package org.example.services.threads;

import org.example.services.GroupFromVkDbService;
import org.example.services.imp.GroupFromVkDbServiceImp;
import org.example.services.utils.graphics.GraphicsDrawer;
import org.example.services.utils.graphics.StudentInVkGroupsDrawer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

/**
 * @author Tribushko Danil
 * @since 30.01.2024
 *
 * Поток для отрисовки графика содержащего распределение студентов среди сообществ вк
 */
public class StudentInVkGroupsThread extends GraphicsDrawerThread implements Runnable{
    private final Session session;
    public StudentInVkGroupsThread(SessionFactory factory) {
        super(factory);
        session = factory.openSession();
    }

    @Override
    public void run() {
        try {
            GroupFromVkDbService groupFromVkDbService = new GroupFromVkDbServiceImp();
            GraphicsDrawer graphicsDrawer = new StudentInVkGroupsDrawer("studentsInVkGroups",
                    groupFromVkDbService.findAll(session));
            graphicsDrawer.drawGraphic();
        } catch (IOException e){
            System.out.println("!!!Ошибка!!! " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
