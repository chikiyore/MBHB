/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mbhb.practica3;
import java.text.DecimalFormat;
import javax.swing.*;
import java.util.Collections;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.data.category.DefaultCategoryDataset;
import java.util.List;


public class Graficador {
    public static void graficar(List<? extends Number> historial, String titulo) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (int i = 0; i < historial.size(); i++) {
        dataset.addValue(historial.get(i).doubleValue(), "Costo", String.valueOf(i));
    }

    double minCosto = historial.stream().mapToDouble(Number::doubleValue).min().orElse(0);
    double maxCosto = historial.stream().mapToDouble(Number::doubleValue).max().orElse(0);

    double margen = (maxCosto - minCosto) * 0.05;

    if (minCosto == maxCosto) {
        minCosto -= 1;
        maxCosto += 1;
    } else {
        minCosto -= margen;
        maxCosto += margen;
    }

    JFreeChart chart = ChartFactory.createLineChart(
            titulo,
            "Iteración",
            "Costo",
            dataset,
            org.jfree.chart.plot.PlotOrientation.VERTICAL,
            true,
            true,
            false
    );

    NumberAxis yAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
    yAxis.setRange(minCosto, maxCosto);
yAxis.setNumberFormatOverride(new DecimalFormat("#,##0.0"));

    
    chart.getCategoryPlot().setDomainGridlinesVisible(false);
    chart.getCategoryPlot().setRangeGridlinesVisible(false);
    chart.getCategoryPlot().setDomainGridlinePaint(java.awt.Color.BLACK);
    chart.getCategoryPlot().setRangeGridlinePaint(java.awt.Color.BLACK);

    ChartPanel chartPanel = new ChartPanel(chart);
    JFrame frame = new JFrame(titulo);
    frame.add(chartPanel);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}

    
    public static void graficarTresLineas(
            List<? extends Number> series1, String label1,
            List<? extends Number> series2, String label2,
            List<? extends Number> series3, String label3,
            String titulo
    ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
        int length = series1.size();
        for (int i = 0; i < length; i++) {
            double v1 = series1.get(i).doubleValue();
            dataset.addValue(v1, label1, String.valueOf(i));
            if (v1 < min) min = v1;
            if (v1 > max) max = v1;
            if (series2 != null && i < series2.size()) {
                double v2 = series2.get(i).doubleValue();
                dataset.addValue(v2, label2, String.valueOf(i));
                if (v2 < min) min = v2;
                if (v2 > max) max = v2;
            }
            if (series3 != null && i < series3.size()) {
                double v3 = series3.get(i).doubleValue();
                dataset.addValue(v3, label3, String.valueOf(i));
                if (v3 < min) min = v3;
                if (v3 > max) max = v3;
            }
        }

        // Ajuste de rango Y con margen
        double margin = (max - min) * 0.05;
        if (min == max) {
            min -= 1;
            max += 1;
        } else {
            min -= margin;
            max += margin;
        }

        JFreeChart chart = ChartFactory.createLineChart(
                titulo,
                "Iteración",
                "Costo",
                dataset,
                org.jfree.chart.plot.PlotOrientation.VERTICAL,
                true, true, false
        );
        NumberAxis yAxis = (NumberAxis) chart.getCategoryPlot().getRangeAxis();
        yAxis.setRange(min, max);
        yAxis.setNumberFormatOverride(new DecimalFormat("#,##0.0"));

        chart.getCategoryPlot().setDomainGridlinesVisible(false);
        chart.getCategoryPlot().setRangeGridlinesVisible(false);

        ChartPanel panel = new ChartPanel(chart);
        JFrame frame = new JFrame(titulo);
        frame.setContentPane(panel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    
    
    
}



