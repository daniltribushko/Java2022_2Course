package org.example.services.threads;

import org.example.models.entities.Module;
import org.example.services.ModuleDbService;
import org.example.services.imp.ModuleDbServiceImp;
import org.example.services.utils.graphics.GraphicsDrawer;
import org.example.services.utils.graphics.ScoresByTaskInModuleDrawer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author Tribushko Danil
 * @since 30.01.2024
 *
 * Поток для отрисовки графика содержащего динамику баллов за задания в каждом модуле
 */
public class ScoresByTaskInModuleThread extends GraphicsDrawerThread implements Runnable{
    private final Session session;
    public ScoresByTaskInModuleThread(SessionFactory factory) {
        super(factory);
        session = factory.openSession();
    }

    @Override
    public void run() {
        try {
            ModuleDbService moduleDbService = new ModuleDbServiceImp();
            List<Module> modules = moduleDbService.findAll(session);
            for (Module module : modules) {
                String moduleNumber = module.getName().split("\\.")[0];
                String fileName = "scoresByTaskInModule" + moduleNumber;
                //Пропускаем последний модуль, т.к в нем только одно задание
                if (moduleNumber.equals("12")){
                    continue;
                }
                GraphicsDrawer graphicsDrawer = new ScoresByTaskInModuleDrawer(fileName, module);
                graphicsDrawer.drawGraphic();
            }
        } catch (IOException e){
            System.out.println("!!!Ошибка!!! " + e.getMessage());
        } finally {
            session.close();
        }

    }
}
