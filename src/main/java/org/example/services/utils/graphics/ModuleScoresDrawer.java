package org.example.services.utils.graphics;

import org.example.models.entities.Module;
import org.example.services.GraphicsService;
import org.example.services.imp.GraphicsServiceImp;
import org.example.services.utils.ChartService;
import org.example.services.utils.DatasetService;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import java.io.IOException;
import java.util.List;

/**
 * @author Tribushko Danil
 * @since 29.01.2024
 *
 * Класс для отрисовки графика содержащего динамику
 * баллов за упражнения, домашнии задания и контрольные вопросы
 */
public class ModuleScoresDrawer extends GraphicsDrawer{
    private final List<Module> modules;
    public ModuleScoresDrawer(String fileName, List<Module> modules) {
        super(fileName);
        this.modules = modules;
    }

    @Override
    public void drawGraphic() throws IOException {
        CategoryDataset dataset = DatasetService.createDatasetModuleScores(modules);
        JFreeChart chart = ChartService.createLineChart(
                dataset,
                "Динамика баллов за упражнения, контрольные вопросы, домашнии задания по модулям",
                "Номер модуля",
                "% баллов"
        );
        GraphicsService graphicsService = new GraphicsServiceImp();
        graphicsService.saveGraphic(chart, fileName);
    }
}
