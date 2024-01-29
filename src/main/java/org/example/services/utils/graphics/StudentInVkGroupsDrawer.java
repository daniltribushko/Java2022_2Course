package org.example.services.utils.graphics;

import org.example.models.entities.GroupFromVk;
import org.example.services.GraphicsService;
import org.example.services.imp.GraphicsServiceImp;
import org.example.services.utils.ChartService;
import org.example.services.utils.DatasetService;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.PieDataset;

import java.io.IOException;
import java.util.List;

/**
 * @author Tribushko Danil
 * @since 29.01.2024
 *
 * Класс для отрисовки графика, содержащего распределение студентов
 * сообществам вк
 */
public class StudentInVkGroupsDrawer extends GraphicsDrawer {
    /**
     * Список сообществ вк
     */
    private final List<GroupFromVk> groups;

    public StudentInVkGroupsDrawer(String fileName, List<GroupFromVk> groups) {
        super(fileName);
        this.groups = groups;
    }

    @Override
    public void drawGraphic() throws IOException {
        PieDataset<String> dataset = DatasetService.createDatasetStudentInVkGroups(groups);
        JFreeChart chart = ChartService.createPieChart(dataset,
                "Распределение студентов вк среди сообществ вк");
        GraphicsService graphicsService = new GraphicsServiceImp();
        graphicsService.saveGraphic(chart, fileName);

    }
}
