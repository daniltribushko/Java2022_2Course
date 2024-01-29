package org.example.services.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.PieDataset;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * @author Tribushko Danil
 * @since 27.01.2024
 * <p>
 * Сервис для создание чартов графиков
 */
public class ChartService {
    private static final Font FONT = new Font("Serial", Font.BOLD, 34);
    private static final Color CHART_BACKGROUND_COLOR = new Color(105, 102, 102);

    private ChartService() {
    }

    /**
     * Создание гистограммы
     *
     * @param dataset    датасет для гистограммы
     * @param titleChart заголовок гистограммы
     * @param titleAxisX название x оси гистограммы
     * @param titleAxisY название y оси гистограммы
     * @return чарт гистограммы
     */
    public static JFreeChart createBarChar(CategoryDataset dataset, String titleChart,
                                           String titleAxisX,
                                           String titleAxisY) {
        JFreeChart chart = ChartFactory.createBarChart(
                null,
                titleAxisX,
                titleAxisY,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                true);
        chart.setTitle(new TextTitle(titleChart, FONT));
        chart.setBackgroundPaint(CHART_BACKGROUND_COLOR);
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setRangeGridlineStroke(new BasicStroke(5.0f));
        BarRenderer.setDefaultBarPainter(new StandardBarPainter());
        BarRenderer renderer = new BarRenderer();
        renderer.setDefaultLegendTextPaint(Color.BLACK);
        renderer.setDefaultLegendTextFont(FONT);
        renderer.setShadowVisible(true);
        renderer.setShadowXOffset(10.0f);
        renderer.setShadowPaint(Color.BLACK);
        plot.setRenderer(renderer);
        setAxisLineAndBarChart(plot);
        return chart;
    }

    /**
     * Создание линейной диаграммы
     *
     * @param dataset    датасет для диаграммы
     * @param titleChart заголовок линейной диаграммы
     * @param titleAxisX название x оси линейной диаграммы
     * @param titleAxisY название y оси линейной диаграммы
     * @return чарт линейной диаграммы
     */
    public static JFreeChart createLineChart(CategoryDataset dataset,
                                             String titleChart,
                                             String titleAxisX,
                                             String titleAxisY) {
        JFreeChart chart = ChartFactory.createLineChart(
                null,
                titleAxisX,
                titleAxisY,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                true
        );
        chart.setBackgroundPaint(CHART_BACKGROUND_COLOR);
        chart.setTitle(new TextTitle(titleChart, FONT));
        CategoryPlot plot = chart.getCategoryPlot();
        //Устанавливаем цвет фойна
        plot.setBackgroundPaint(Color.WHITE);
        //Включаем отображение линий осей plot и устанавливаем их цвет
        plot.setDomainGridlinesVisible(true);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setDomainGridlinePaint(Color.BLACK);
        //Устанавливаем цвет фойна
        plot.setBackgroundPaint(Color.WHITE);
        setAxisLineAndBarChart(plot);
        LineAndShapeRenderer render = new LineAndShapeRenderer();
        //Отключаем автоматический размер строк
        render.setAutoPopulateSeriesStroke(false);
        //Устанавливаем ширину строк
        render.setDefaultStroke(new BasicStroke(10.0f));
        //Устанавливаем шрифт для легенд
        render.setDefaultLegendTextFont(FONT);
        //Отключаем автоматический размер меток
        render.setAutoPopulateSeriesShape(false);
        render.setDefaultShape(new Rectangle2D.Double(-10.0, -10.0,
                20.0, 20.0));
        return chart;
    }

    /**
     * Создание круговой диаграммы
     *
     * @param dataset    датасет для круговой диаграммы
     * @param titleChart заголовок диаграммы
     * @return круговую диаграмму
     */
    public static JFreeChart createPieChart(PieDataset<?> dataset,
                                            String titleChart) {
        JFreeChart chart = ChartFactory.createPieChart(
                null,
                dataset,
                false,
                true,
                false
        );
        chart.setTitle(new TextTitle(titleChart, FONT));
        chart.setBackgroundPaint(CHART_BACKGROUND_COLOR);
        PiePlot<?> plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDefaultSectionOutlinePaint(Color.BLACK);
        plot.setDefaultSectionOutlineStroke(new BasicStroke(40.0f));
        plot.setLabelFont(new Font("Serial", Font.BOLD, 36));
        plot.setLabelPaint(Color.BLACK);
        plot.setLabelLinkPaint(Color.BLACK);
        plot.setLabelLinkStroke(new BasicStroke(4.0f));
        plot.setLabelBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        return chart;
    }

    /**
     * Установка подписей осей линейной диаграммы и гистограммы
     *
     * @param plot графическая часть
     */
    private static void setAxisLineAndBarChart(CategoryPlot plot) {
        ValueAxis axisX = plot.getRangeAxis();
        CategoryAxis axisY = plot.getDomainAxis();
        axisX.setLabelFont(FONT);
        axisX.setLabelPaint(Color.BLACK);
        axisX.setTickLabelPaint(Color.BLACK);
        axisX.setTickLabelFont(FONT);
        axisY.setLabelFont(FONT);
        axisY.setLabelPaint(Color.BLACK);
        axisY.setTickLabelPaint(Color.BLACK);
        axisY.setTickLabelFont(FONT);
    }
}
