package org.example.services.imp;

import org.example.services.ConfigService;

import java.io.*;
import java.util.Properties;

/**
 * @author Tribushko Danil
 * @since 24.01.2024
 *
 * Реализация сервиса по работе с файлом config.properties
 */
public class ConfigServiceImp implements ConfigService {
    private final Properties properties;

    public ConfigServiceImp(String propertyPath){
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(new File(propertyPath))){
            properties.load(fileInputStream);
        } catch (IOException e ) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
