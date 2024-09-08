package org.llan.loggedrl.logviewer.ui;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;

public class Graph {
    private LineChart<Number, Number> _lineChart;

    public Graph(List<Double> data) {
        if(data == null || data.size() == 0){
            _lineChart = new LineChart<>(new NumberAxis(), new NumberAxis());
        } else {
            NumberAxis xAxis = new NumberAxis();
            NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Time step");
            yAxis.setLabel("Value");

            _lineChart = new LineChart<>(xAxis, yAxis);

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            for (int i = 0; i < data.size(); i++) {
                series.getData().add(new XYChart.Data<>(i, data.get(i)));
            }

            _lineChart.setLegendVisible(false);
            _lineChart.getData().add(series);
        }

    }

    public LineChart<Number, Number> getLineChart() {
        return _lineChart;
    }
}

