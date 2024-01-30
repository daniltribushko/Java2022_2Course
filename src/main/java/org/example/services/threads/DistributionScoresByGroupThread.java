package org.example.services.threads;

import org.example.models.entities.Module;
import org.example.services.ModuleDbService;
import org.example.services.StudentDbService;
import org.example.services.imp.ModuleDbServiceImp;
import org.example.services.imp.StudentDbServiceImp;
import org.example.services.utils.graphics.DistributionScoresByGroupsDrawer;
import org.example.services.utils.graphics.GraphicsDrawer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author Tribushko Danil
 * @since 30.01.2024
 *
 * Поток для отрисовки графика, содержащего распределение баллов среди всех групп студентов
 */
public class DistributionScoresByGroupThread extends GraphicsDrawerThread implements Runnable {
    private final Session session;

    public DistributionScoresByGroupThread(SessionFactory factory) {
        super(factory);
        session = factory.openSession();
    }

    @Override
    public void run() {
        try {
            ModuleDbService moduleDbService = new ModuleDbServiceImp();
            StudentDbService studentDbService = new StudentDbServiceImp();
            List<Module> modules = moduleDbService.findAll(session);
            int countStudentsGroup1 = studentDbService.findAllByGroup(session, 1).size();
            int countStudentsGroup2 = studentDbService.findAllByGroup(session, 2).size();
            int countStudentsGroup3 = studentDbService.findAllByGroup(session, 3).size();
            GraphicsDrawer drawer = new DistributionScoresByGroupsDrawer("distributionScoresByGroup",
                    modules, countStudentsGroup1, countStudentsGroup2, countStudentsGroup3);
            drawer.drawGraphic();
        } catch (IOException e) {
           System.out.println("!!!Ошибка!!! " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
