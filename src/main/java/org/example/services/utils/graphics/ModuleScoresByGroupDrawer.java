package org.example.services.utils.graphics;

import org.example.models.entities.Module;
import org.example.models.entities.Student;
import org.example.services.GraphicsService;
import org.example.services.imp.GraphicsServiceImp;
import org.example.services.utils.ChartService;
import org.example.services.utils.DatasetService;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Tribushko Danil
 * @since 29.01.2024
 *
 * Класс для отрисовки графика, содержащего динамику баллов
 * за модули у студентов каждой группы
 */
public class ModuleScoresByGroupDrawer extends GraphicsDrawer {
    private final List<Module> modules;
    private final Map<Integer, List<Student>> studentData;

    public ModuleScoresByGroupDrawer(
            String fileName,
            List<Module> modules,
            Map<Integer, List<Student>> studentData) {
        super(fileName);
        this.modules = modules;
        this.studentData = studentData;
    }

    @Override
    public void drawGraphic() throws IOException {
        CategoryDataset dataset = DatasetService
                .createDatasetModuleScoresByGroup(modules, studentData);
        JFreeChart chart = ChartService.createLineChart(dataset,
                "Динамика получения баллов студентов для каждой группы",
                "Номер модуля", "% баллов студентов");
        GraphicsService graphicsService = new GraphicsServiceImp();
        graphicsService.saveGraphic(chart, fileName);

    }
}
