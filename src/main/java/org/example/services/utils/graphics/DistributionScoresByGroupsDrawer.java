package org.example.services.utils.graphics;

import org.example.models.entities.Module;
import org.example.services.GraphicsService;
import org.example.services.imp.GraphicsServiceImp;
import org.example.services.utils.ChartService;
import org.example.services.utils.DatasetService;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

import java.io.IOException;
import java.util.List;

/**
 * @author Tribushko Danil
 * @since 29.01.2024
 *
 * Класс для отрисовки графика, содержащего распределение баллов
 * среди студентов каждой группы
 */
public class DistributionScoresByGroupsDrawer extends GraphicsDrawer {
    /**
     * Список модулей
     */
    private final List<Module> modules;

    /**
     * Количество студентов в первой группе
     */
    private final int countStudentsGroup1;

    /**
     * Количество студеннтов во второй группе
     */
    private final int countStudentsGroup2;

    /**
     * Количество студентов в третьей группе
     */
    private final int countStudentsGroup3;

    public DistributionScoresByGroupsDrawer(String fileName,
                                               List<Module> modules,
                                               int countStudentsGroup1,
                                               int countStudentsGroup2,
                                               int countStudentsGroup3) {
        super(fileName);
        this.modules = modules;
        this.countStudentsGroup1 = countStudentsGroup1;
        this.countStudentsGroup2 = countStudentsGroup2;
        this.countStudentsGroup3 = countStudentsGroup3;
    }

    @Override
    public void drawGraphic() throws IOException {
        PieDataset<String> dataset = DatasetService.createDatasetDistributionScoresByGroups(modules,
                countStudentsGroup1,
                countStudentsGroup2,
                countStudentsGroup3);
        JFreeChart chart = ChartService.createPieChart(dataset,
                "Распределение баллов среди групп студентов");
        GraphicsService graphicsService = new GraphicsServiceImp();
        graphicsService.saveGraphic(chart, fileName);
    }
}
