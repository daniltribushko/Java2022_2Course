package org.example.services;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import org.example.models.vk.VkGroup;

import java.util.Optional;

/**
 * @author Tribushko Danil
 * @since 24.01.2024
 * <p>
 * Сервис для работы с сообществами вк
 */
public interface VKApiGroupService {
    /**
     * Получение сообщества вк по его названию
     *
     * @param apiClient клиент api вк
     * @param actor     пользователь вк для отправки запроса
     * @param name      название сообщества
     * @return сообщество вк
     */
    Optional<VkGroup> getGroup(VkApiClient apiClient, UserActor actor, String name) throws ClientException, ApiException, InterruptedException;

    /**
     * Проверка является ли пользователь участником сообщества
     *
     * @param apiClient клиент api вк
     * @param actor     пользователь вк для отправки запроса
     * @param groupName название сообщества
     * @param userId    идентификатор пользователя
     * @return логическое значение является ли пользователь участником сообщества
     */
    boolean isMember(VkApiClient apiClient, UserActor actor, String groupName, Long userId) throws ClientException, ApiException, InterruptedException;
}
