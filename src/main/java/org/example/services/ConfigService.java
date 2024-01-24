package org.example.services;

/**
 * @author Tribushko Danil
 * @since 24.01.2024
 * <p>
 * Сервис дял работы с файлом config.properties
 */
public interface ConfigService {
    /**
     * Получение значения свойства
     *
     * @param key ключ свойства
     * @return значение свойства
     */
    String getProperty(String key);
}
