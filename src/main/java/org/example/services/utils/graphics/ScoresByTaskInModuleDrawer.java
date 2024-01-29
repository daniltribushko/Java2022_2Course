package org.example.services.utils.graphics;

import org.example.models.entities.Module;
import org.example.services.GraphicsService;
import org.example.services.imp.GraphicsServiceImp;
import org.example.services.utils.ChartService;
import org.example.services.utils.DatasetService;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import java.io.IOException;

/**
 * @author Tribushko Danil
 * @since 29.01.2024
 *
 * Класс для отрисовки баллов, содержащего динамику баллов за задания в конкретном модуле
 */
public class ScoresByTaskInModuleDrawer extends GraphicsDrawer{
    private final Module module;
    public ScoresByTaskInModuleDrawer(String fileName, Module module) {
        super(fileName);
        this.module = module;
    }

    @Override
    public void drawGraphic() throws IOException {
        CategoryDataset dataset = DatasetService.createScoresByTasksInModule(module);
        JFreeChart chart = ChartService.createLineChart(dataset,
                "Динамика баллов за задания в модуле " + module.getName(),
                "Название задания",
                "% баллов");
        GraphicsService graphicsService = new GraphicsServiceImp();
        graphicsService.saveGraphic(chart,
                fileName);
    }
}
