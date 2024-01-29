package org.example.services.utils.graphics;

import java.io.IOException;

/**
 * @author Tribushko Danil
 * @since 29.01.2024
 * <p>
 * Абстрактный класс по отрисовке графиков
 */
public abstract class GraphicsDrawer {
    /**
     * Расширение графиков
     */
    private static final String EXTENSION = ".jpeg";

    /**
     * Директория для сохранения графиков
     */
    private static final String DIRECTORY = "src/main/resources/charts/";

    /**
     * Название файла
     */
    protected String fileName;

    protected GraphicsDrawer(String fileName) {
        this.fileName = DIRECTORY + fileName + EXTENSION;
    }

    /**
     * Отрисвока графика
     */
    public abstract void drawGraphic() throws IOException;
}
