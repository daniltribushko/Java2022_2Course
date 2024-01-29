package org.example.services.imp;

import org.example.services.GraphicsService;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Tribushko Danil
 * @since 29.01.2024
 *
 * Реализация класса для работы с графиками
 */
public class GraphicsServiceImp implements GraphicsService {


    @Override
    public void saveGraphic(JFreeChart chart,
                            String fileName) throws IOException {
        try {
            ChartUtils.saveChartAsJPEG(new File(fileName), chart, 2160, 890);
        } catch (FileAlreadyExistsException e) {
            deleteGraphic(fileName);
            saveGraphic(chart, fileName);
        } catch (FileNotFoundException e){
           Files.createFile(Path.of(fileName));
           saveGraphic(chart, fileName);
        }
    }

    @Override
    public void deleteGraphic(String fileName) {
        try {
            Files.delete(Path.of(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
