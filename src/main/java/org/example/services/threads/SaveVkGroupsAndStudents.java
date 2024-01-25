package org.example.services.threads;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.example.models.entities.GroupFromVk;
import org.example.models.entities.Student;
import org.example.models.entities.StudentFromVk;
import org.example.models.vk.VkGroup;
import org.example.models.vk.VkStudent;
import org.example.services.*;
import org.example.services.imp.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class SaveVkGroupsAndStudents implements Runnable{

    private final Session session;


    public SaveVkGroupsAndStudents(Session session){
        this.session = session;
    }

    @Override
    public void run() {
        VkApiService vkApiService = new VkApiServiceImp();
        UserActor actor = vkApiService.getActor();
        VkApiClient apiClient = vkApiService.getApiClient();
        try {
            saveGroupsFromVk(session, apiClient, actor);
            saveStudentsVkInDb(session, apiClient, actor);
            saveStudentsWithGroups(session, apiClient, actor);
        } catch (ClientException | ApiException | InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }

    /**
     * Получение студентов из вк
     * @param session сессия бд
     * @param apiClient  клиент api vk
     * @param actor      пользователь вк для отправки запросов
     * @param studentsDb список студентов из бд
     * @return список студентов вк
     */
    private List<VkStudent> getStudents(Session session,
                                        VkApiClient apiClient,
                                        UserActor actor, List<Student> studentsDb) {
        List<VkStudent> students = new ArrayList<>();
        VkApiStudentService studentService = new VkApiStudentServiceImp();
        GroupFromVkDbService groupFromVkDbService = new GroupFromVkDbServiceImp();
        List<GroupFromVk> vkGroups = groupFromVkDbService.findAll(session);
        studentsDb.forEach(s -> {
            try {
                VkStudent vkStudent = studentService.getStudent(apiClient,
                        actor,
                        s.getFullName(),
                        Long.parseLong(vkGroups.get(0).getVkId()));
                if (vkStudent == null) {
                    vkStudent = studentService.getStudent(
                            apiClient,
                            actor,
                            s.getFullName(),
                            Long.parseLong(vkGroups.get(1).getVkId())
                    );
                    if (vkStudent == null) {
                        vkStudent = studentService.getStudent(
                                apiClient,
                                actor,
                                s.getFullName(),
                                Long.parseLong(vkGroups.get(2).getVkId())
                        );
                    }
                }
                if (vkStudent != null) {
                    students.add(vkStudent);
                    System.out.println(vkStudent);
                }
            } catch (ClientException | ApiException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        });
        return students;
    }

    /**
     * Связывание студентов из вк с сообществами вк
     * @param session сессия бд
     * @param apiClient клиент вк api
     * @param actor пользователь для отправки запросов
     */
    private void saveStudentsWithGroups(Session session, VkApiClient apiClient,
                                        UserActor actor) throws ClientException, InterruptedException, ApiException {
        GroupFromVkDbService dbGroupVkService = new GroupFromVkDbServiceImp();
        StudentFromVkDbService dbStudentVkService = new StudentFromVkDbServiceImp();
        VKApiGroupService groupVkService = new VkApiGroupServiceImp();
        //Получаем студентов вк из бд
        List<StudentFromVk> students = dbStudentVkService.findAll(session);
        //Получаем сообщества вк из бд
        List<GroupFromVk> groups = dbGroupVkService.findAll(session);
        for (GroupFromVk group : groups) {
            for (StudentFromVk student : students) {
                //Если пользователь состоит в сообществе, то связываем его с сообществом
                if (groupVkService.isMember(apiClient,
                        actor,
                        group.getVkId(),
                        student.getVkId())) {
                    group.addStudent(student);
                }
            }
            dbGroupVkService.update(session, group);
        }
    }

    /**
     * Сохранение сообществ из вк
     * @param session сессия бд
     * @param apiClient клиент api вк
     * @param actor пользователь для отправки запросов
     */
    private void saveGroupsFromVk(Session session,
                                  VkApiClient apiClient,
                                  UserActor actor) throws ClientException, ApiException, InterruptedException {
        GroupFromVkDbService dbService = new GroupFromVkDbServiceImp();
        getGroupsFromVk(apiClient, actor).forEach(g -> dbService
                .save(session, new GroupFromVk(g.getName(), String.valueOf(g.getId()))));
    }

    /**
     * Сохранение студентов вк
     *
     * @param session сессия бд
     * @param apiClient клиент api вк
     * @param actor пользователь для отправки запросов
     */
    private void saveStudentsVkInDb(Session session,
                                    VkApiClient apiClient,
                                    UserActor actor){
        StudentFromVkDbService dbServiceVk = new StudentFromVkDbServiceImp();
        StudentDbService dbService = new StudentDbServiceImp();
        List<VkStudent> vkStudents = getStudents(session, apiClient, actor,
                dbService.findAll(session));
        vkStudents.forEach(v -> dbServiceVk.save(session,
                new StudentFromVk(v.getName(), v.getVkId(), v.getGender(), v.getCity())));
    }
    /**
     * Получение сообществ из вк
     *
     * @param apiClient клиент api vk
     * @param actor     пользователь вк для отправки запросов
     * @return список сообществ вк
     */
    private List<VkGroup> getGroupsFromVk(VkApiClient apiClient,
                                          UserActor actor) throws ClientException, ApiException, InterruptedException {
        VKApiGroupService groupService = new VkApiGroupServiceImp();
        List<VkGroup> result = new ArrayList<>();
        VkGroup group = groupService.getGroup(apiClient, actor,
                        "Уральский федеральный университет | УрФУ")
                .orElse(null);
        VkGroup group2 = groupService.getGroup(apiClient, actor, "Третий курс ИОТ, УрФУ")
                .orElse(null);
        VkGroup group3 = groupService.getGroup(apiClient, actor, "СОЮЗ СТУДЕНТОВ ИРИТ-РТФ УрФУ")
                .orElse(null);
        if (group != null) {
            result.add(group);
        }
        if (group2 != null) {
            result.add(group2);
        }
        if (group3 != null) {
            result.add(group3);
        }
        return result;
    }
}
