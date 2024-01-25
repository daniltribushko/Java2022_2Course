package org.example.services.imp;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.base.Sex;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.UserFull;
import org.example.models.enums.Gender;
import org.example.models.vk.VkStudent;
import org.example.services.VkApiStudentService;

/**
 * @author Tribushko Danil
 * @since 24.01.2024
 * <p>
 * Реализация сервиса по работе со студентами вк
 */
public class VkApiStudentServiceImp implements VkApiStudentService {
    @Override
    public VkStudent getStudent(VkApiClient vk, UserActor actor, String fullName, Long groupId) throws ClientException, ApiException, InterruptedException {
        UserFull user = getUser(vk, actor, fullName, groupId);
        String oldFullName = fullName;
        if (user == null) {
            String[] fullNameMas = fullName.split(" ");
            fullName = fullNameMas[1] + " " + fullNameMas[0];
            user = getUser(vk, actor, fullName, groupId);
        }
        VkStudent student = null;
        if (user != null) {
            user.getSex();
            student = new VkStudent(user.getId(),
                    oldFullName,
                    getGender(user.getSex()));
        }
        return student;
    }

    /**
     * Получение пола студента
     *
     * @param sex пол пользователя вк
     * @return пол студента
     */
    private Gender getGender(Sex sex) {
        Gender gender = null;
        if (sex != null) {
            if (sex == Sex.FEMALE) {
                gender = Gender.FEMALE;
            } else if (sex == Sex.MALE) {
                gender = Gender.MALE;
            }
        }
        return gender;
    }

    /**
     * Получение пользователя вк
     *
     * @param vk       клиент api вк
     * @param actor    пользователь вк для отправки запросов
     * @param fullName полное имя студента
     * @param groupId  идентификатор сообщества
     * @return пользователь вк
     */
    private UserFull getUser(VkApiClient vk, UserActor actor, String fullName, Long groupId) throws ClientException, ApiException, InterruptedException {
        Thread.sleep(500);
        return vk.users()
                .search(actor)
                .q(fullName)
                .fields(Fields.NICKNAME, Fields.SEX, Fields.CITY)
                .groupId(groupId)
                .execute()
                .getItems()
                .stream()
                .filter(u -> fullName.equals(u.getLastName() + " " + u.getFirstName()) ||
                        fullName.equals(u.getFirstName() + " " + u.getLastName()))
                .findFirst()
                .orElse(null);
    }
}
