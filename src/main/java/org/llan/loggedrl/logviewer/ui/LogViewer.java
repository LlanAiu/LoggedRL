package org.llan.loggedrl.logviewer.ui;

import javafx.scene.Parent;
import javafx.scene.chart.Chart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.llan.loggedrl.logviewer.data.Log;
import org.llan.loggedrl.logviewer.data.LogParser;

import java.io.File;
import java.util.List;

public class LogViewer {
    private Stage _reference;

    private VBox _root;
    private HBox _content;
    private Navigation _navigation;
    private Sidebar _sidebar;
    private Graph _graph;
    private Log[] _logs;

    private List<Double> _selectedData;

    private int _currentLogIndex = 0;

    public static LogViewer _instance;

    public static LogViewer getInstance(){
        if(_instance == null){
            _instance = new LogViewer();
        }
        return _instance;
    }

    private LogViewer(){
        _root = new VBox();
        _navigation = new Navigation();
        _sidebar = new Sidebar();
        _root.getChildren().add(_navigation.getMenuBar());
        _content = new HBox();
        _root.getChildren().add(_content);
        _content.getChildren().add(_sidebar.getRoot());
        _content.getChildren().add(new Graph(null).getLineChart());
    }

    public void setStageReference(Stage stage){
        _reference = stage;
        _navigation.addSelector(_reference);
    }

    public Parent getRoot(){
        return _root;
    }

    public void setLogs(File file){
        _logs = LogParser.parseLogFile(file);
        _sidebar.setLogSize(_logs.length);
        _currentLogIndex = 0;
        _sidebar.updateLogDetails(_logs[_currentLogIndex]);
        selectData();
    }

    public void changeLogIndex(int index){
        _currentLogIndex = index;
        _sidebar.updateLogDetails(_logs[_currentLogIndex]);
        clearData();
    }

    public void clearData(){
        _content.getChildren().removeIf(node -> node instanceof Chart);
        _graph = new Graph(null);
        _content.getChildren().add(_graph.getLineChart());
    }

    public void selectData(){
        _content.getChildren().removeIf(node -> node instanceof Chart);

        _selectedData = _logs[_currentLogIndex].getSelected();

        _graph = new Graph(_selectedData);
        _content.getChildren().add(_graph.getLineChart());
    }
}
