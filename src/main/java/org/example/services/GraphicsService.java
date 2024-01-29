package org.example.services;

import org.jfree.chart.JFreeChart;

import java.io.IOException;

/**
 * @author Tribushko Danil
 * @since 27.01.2024
 * <p>
 * Сервисв для работы с графиками
 */
public interface GraphicsService {
    /**
     * Сохранение графика в виде файла
     *
     * @param chart    график
     * @param fileName название файла
     */
    void saveGraphic(JFreeChart chart,
                     String fileName) throws IOException;

    /**
     * Удаление графика
     *
     * @param fileName название файла
     */
    void deleteGraphic(String fileName);
}
