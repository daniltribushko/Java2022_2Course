package org.example.services;

import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;

/**
 * @author Tribushko Danil
 * @since 24.01.2024
 * <p>
 * Сервис для работы с api вк
 */
public interface VkApiService {
    /**
     * Получение клиента vk api
     *
     * @return клиент vk api
     */
    VkApiClient getApiClient();

    /**
     * Получение пользователя вк для отправки запросов
     *
     * @return пользователь вк для отправки запросов
     */
    UserActor getActor();
}
