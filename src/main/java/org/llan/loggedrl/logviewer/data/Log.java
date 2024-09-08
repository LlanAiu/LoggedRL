package org.llan.loggedrl.logviewer.data;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import org.llan.loggedrl.logviewer.ui.LogViewer;
import org.llan.loggedrl.logviewer.ui.UIConstants;

import java.util.ArrayList;
import java.util.List;

public class Log {
    private List<Double> _gradientNorms;
    private List<Double> _rewards;
    private List<Double> _averageRewards;

    private List selected;

    private int _perspective;

    public void parse(List<String> logLines){
        _gradientNorms = new ArrayList<>();
        _rewards = new ArrayList<>();
        _averageRewards = new ArrayList<>();
        _perspective = Integer.parseInt(logLines.get(0).split(":")[1].trim());
        for(int i = 1; i < logLines.size(); i++){
            String[] data = logLines.get(i).split(":");
            String header = data[0].trim();
            String[] values = data[1].split(",");
            List toAdd;
            switch(header){
                case "Gradient Norms":
                    toAdd = _gradientNorms;
                    break;
                case "Rewards":
                    toAdd = _rewards;
                    break;
                case "Average Rewards":
                    toAdd = _averageRewards;
                    break;
                default:
                    toAdd = null;
            }
            if(toAdd != null){
                for(int j = 0; j < values.length; j++){
                    toAdd.add(Double.parseDouble(values[j]));
                }
            }
        }
    }

    public Label[] getLabels(){
        Label[] labels = new Label[3];

        for(int i = 0; i < labels.length; i++){
            String name = "";
            switch(i){
                case 0:
                    name = "Gradient Norms";
                    break;
                case 1:
                    name = "Rewards";
                    break;
                case 2:
                    name = "Average Rewards";
                    break;
            }
            labels[i] = new Label(name);
            labels[i].setPrefSize(UIConstants.SIDEBAR_WIDTH, 45);
            labels[i].setFont(UIConstants.LABEL_FONT);
            labels[i].setPadding(new Insets(0, 0, 0, 10));
            int index = i;
            labels[i].setOnMouseEntered(e ->
                    labels[index].setBackground(
                            new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY))
                    )
            );
            labels[i].setOnMouseExited(e ->
                    labels[index].setBackground(
                            new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))
                    )
            );
            labels[i].setOnMouseClicked(e -> {
                setSelected(labels[index].getText());
                LogViewer.getInstance().selectData();
            });
        }

        return labels;
    }

    public List<Double> getSelected(){
        return selected;
    }

    private void setSelected(String name){
        switch(name){
            case "Gradient Norms":
                selected = _gradientNorms;
                break;
            case "Rewards":
                selected = _rewards;
                break;
            case "Average Rewards":
                selected = _averageRewards;
                break;
        }
    }

    @Override
    public String toString() {
        return "PlayerLog{" +
                "_averageRewards=" + _averageRewards +
                ", _gradientNorms=" + _gradientNorms +
                ", _rewards=" + _rewards +
                ", _perspective=" + _perspective +
                '}';
    }
}
