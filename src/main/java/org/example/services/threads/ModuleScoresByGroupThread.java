package org.example.services.threads;

import org.example.models.entities.Module;
import org.example.models.entities.Student;
import org.example.services.ModuleDbService;
import org.example.services.StudentDbService;
import org.example.services.imp.ModuleDbServiceImp;
import org.example.services.imp.StudentDbServiceImp;
import org.example.services.utils.graphics.GraphicsDrawer;
import org.example.services.utils.graphics.ModuleScoresByGroupDrawer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Tribushko Danil
 * @since 30.01.2024
 *
 * Поток для отрисовки графика содержащего динамику баллов студентов каждой группы среди модулей
 */
public class ModuleScoresByGroupThread extends GraphicsDrawerThread implements Runnable {
    private final Session session;

    public ModuleScoresByGroupThread(SessionFactory factory) {
        super(factory);
        session = factory.openSession();
    }

    @Override
    public void run() {
        try {
            ModuleDbService moduleDbService = new ModuleDbServiceImp();
            StudentDbService studentDbService = new StudentDbServiceImp();
            List<Module> modules = moduleDbService.findAll(session);
            Map<Integer, List<Student>> studentData = new TreeMap<>();
            studentData.put(1, studentDbService.findAllByGroup(session, 1));
            studentData.put(2, studentDbService.findAllByGroup(session, 2));
            studentData.put(3, studentDbService.findAllByGroup(session, 3));
            GraphicsDrawer graphicsDrawer = new ModuleScoresByGroupDrawer("moduleScoresByGroup",
                    modules,
                    studentData);
            graphicsDrawer.drawGraphic();
        } catch (IOException e) {
            System.out.println("!!!Ошибка!!! " + e.getMessage());
        } finally {
            session.close();
        }
    }
}
