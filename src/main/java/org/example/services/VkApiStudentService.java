package org.example.services;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.example.models.vk.VkStudent;

/**
 * @author Tribushko Danil
 * @since 24.01.2024
 * <p>
 * Сервис для работы с студентами вк
 */
public interface VkApiStudentService {
    /**
     * Получение студента вк
     *
     * @param vk клиент api вк
     * @param actor пользователь вк для отправки запросов
     * @param fullName полное имя студента
     * @param groupId индетификатор группы вк
     * @return студент вк
     */
    VkStudent getStudent(VkApiClient vk, UserActor actor, String fullName, Long groupId) throws ClientException, ApiException, InterruptedException;
}
