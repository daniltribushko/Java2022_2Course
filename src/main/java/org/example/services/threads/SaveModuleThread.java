package org.example.services.threads;

import org.example.models.csv.ModuleCsv;
import org.example.models.csv.ModuleScoreCsv;
import org.example.models.csv.TaskCsv;
import org.example.models.csv.TaskScoreCsv;
import org.example.models.entities.*;
import org.example.models.entities.Module;
import org.example.services.ModuleDbService;
import org.example.services.StudentDbService;
import org.example.services.imp.ModuleDbServiceImp;
import org.example.services.imp.StudentDbServiceImp;
import org.hibernate.Session;

import java.util.List;

/**
 * @author Tribushko Danil
 * @since 23.01.2024
 *
 * Поток для сохранения модулей в бд
 */
public class SaveModuleThread implements Runnable{
    private final List<ModuleCsv> modules;
    private final Session session;

    public SaveModuleThread(Session session, List<ModuleCsv> modules){
        this.modules = modules;
        this.session = session;
    }
    @Override
    public void run() {
        StudentDbService studentDbService = new StudentDbServiceImp();
        ModuleDbService moduleDbService = new ModuleDbServiceImp();
        for (ModuleCsv moduleCsv : modules){
            Module module = new Module(moduleCsv.getName(),
                    moduleCsv.getMaxScoreByExercise(),
                    moduleCsv.getMaxScoreByControlQuestion(),
                    moduleCsv.getMaxScoreByHomework());
            for (ModuleScoreCsv moduleScoreCsv : moduleCsv.getScores()){
                ModuleScore moduleScore = new ModuleScore(moduleScoreCsv.getScoreByExercise(),
                        moduleScoreCsv.getScoreByControlQuestion(),
                        moduleScoreCsv.getScoreByHomeWork());
                moduleScore.setStudent(studentDbService.findByFullName(session,
                        moduleScoreCsv.getStudent().getFullName()).get());
                module.addModuleScore(moduleScore);
            }
            for (TaskCsv task : moduleCsv.getTasks()) {
                Task taskDb = new Task(task.getName(), task.getType(), task.getMaxScore());
                for (TaskScoreCsv taskScoreCsv : task.getScores()){
                    TaskScore taskScore = new TaskScore(taskScoreCsv.getScore());
                    taskDb.addTaskScore(taskScore);
                    taskScore.setStudent(studentDbService.findByFullName(session,
                            taskScoreCsv.getStudent().getFullName()).get());
                }
                module.addTask(taskDb);
            }
            moduleDbService.save(session, module);
            System.out.println("Модуль сохранен");
        }
        session.close();
    }
}
