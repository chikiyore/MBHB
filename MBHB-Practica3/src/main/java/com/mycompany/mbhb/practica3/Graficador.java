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

    
    
    
}



