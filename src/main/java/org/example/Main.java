package org.example;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.example.models.csv.*;
import org.example.models.dao.StudentDao;
import org.example.models.dao.imp.StudentDaoImp;
import org.example.models.entities.*;
import org.example.services.*;
import org.example.services.imp.*;
import org.example.services.threads.SaveModuleThread;
import org.example.services.threads.SaveVkGroupsAndStudents;
import org.example.services.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.*;

public class Main {
    private List<StudentCsv> students;

    public static void main(String[] args) throws ClientException, ApiException, InterruptedException, IOException {
        Main main = new Main();
        SessionFactory factory = HibernateUtils.getSessionFactory();
        List<ModuleCsv> allModules = main.getAllModules();
        //main.saveStudents(factory.openSession(), students);
        //main.saveModulesInDb(factory, allModules);
        //main.saveStudentAndGroupsInVk(factory);
        //main.saveStudentWithVkStudent(factory);

    }

    private void saveStudentWithVkStudent(SessionFactory factory){
        Session session = factory.openSession();
        StudentDbService studentDbService = new StudentDbServiceImp();
        StudentFromVkDbService studentFromVkDbService = new StudentFromVkDbServiceImp();
        for (Student student : studentDbService.findAll(session)){
            StudentFromVk studentFromVk = studentFromVkDbService.findByFullName(session, student.getFullName())
                    .orElse(null);
            if(studentFromVk != null){
                studentFromVk.setStudent(student);
                student.setStudentVk(studentFromVk);
                studentDbService.update(session, student);
            }
        }
        session.close();
    }


    /**
     * Сохранение модулей в бд
     */
    private void saveModulesInDb(SessionFactory factory, List<ModuleCsv> modulesCsv) {
        Thread thread = new Thread(new SaveModuleThread(factory.openSession(),
                List.of(modulesCsv.get(0),
                        modulesCsv.get(3),
                        modulesCsv.get(6),
                        modulesCsv.get(9))));
        Thread thread2 = new Thread(new SaveModuleThread(factory.openSession(),
                List.of(modulesCsv.get(1),
                        modulesCsv.get(4),
                        modulesCsv.get(7),
                        modulesCsv.get(10))));
        Thread thread3 = new Thread(new SaveModuleThread(factory.openSession(),
                List.of(modulesCsv.get(2),
                        modulesCsv.get(5),
                        modulesCsv.get(8),
                        modulesCsv.get(11))));
        thread.start();
        thread2.start();
        thread3.start();
        try {
            thread.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    /**
     * Сохранение студентов в бд
     *
     * @param session  сессия бд
     * @param students список студентов из csv
     */
    private void saveStudents(Session session, List<StudentCsv> students) {
        StudentDao studentDao = new StudentDaoImp();
        students.forEach(s -> studentDao.save(session,
                new Student(s.getFullName(),
                        s.getULearnId(),
                        s.getGroup())));
        session.close();
    }

    /**
     * Получение списка модулей из всех csv файлов
     *
     * @return список модулей из всех csv файлов
     */
    private List<ModuleCsv> getAllModules() {
        CsvParser parser = new CsvParserImp();
        List<ModuleCsv> allModules = new ArrayList<>();
        List<ModuleCsv> modules1 = parser.getModuleFromCsv(1);
        List<ModuleCsv> modules2 = parser.getModuleFromCsv(2);
        List<ModuleCsv> modules3 = parser.getModuleFromCsv(3);
        students = parser.getStudents();
        for (int i = 0; i < modules1.size(); i++) {
            ModuleCsv module1 = modules1.get(i);
            ModuleCsv module2 = modules2.get(i);
            ModuleCsv module3 = modules3.get(i);
            List<TaskCsv> tasks1 = module1.getTasks();
            List<TaskCsv> tasks2 = module2.getTasks();
            List<TaskCsv> tasks3 = module3.getTasks();
            List<TaskCsv> tasks = new ArrayList<>();
            for (int j = 0; j < module1.getTasks().size(); j++) {
                List<TaskScoreCsv> taskScores = new ArrayList<>();
                TaskCsv task = tasks1.get(j);
                taskScores.addAll(task.getScores());
                taskScores.addAll(tasks2.get(j).getScores());
                taskScores.addAll(tasks3.get(j).getScores());
                task.setScores(taskScores);
                tasks.add(task);
            }
            ModuleCsv finalModule = new ModuleCsv(module1.getName(),
                    module1.getMaxScoreByHomework(),
                    module1.getMaxScoreByControlQuestion(),
                    module1.getMaxScoreByExercise(), tasks);
            List<ModuleScoreCsv> moduleScoreCsv = new ArrayList<>();
            moduleScoreCsv.addAll(module1.getScores());
            moduleScoreCsv.addAll(module2.getScores());
            moduleScoreCsv.addAll(module3.getScores());
            finalModule.setScores(moduleScoreCsv);
            allModules.add(finalModule);
        }
        return allModules;
    }

    private void saveStudentAndGroupsInVk(SessionFactory factory)  {
        Thread thread = new Thread(new SaveVkGroupsAndStudents(factory.openSession()));
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }
}