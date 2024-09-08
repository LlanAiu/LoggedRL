package org.llan.loggedrl.logviewer.ui;


import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.llan.loggedrl.logviewer.data.Log;

public class Sidebar {
    private VBox _root;
    private ChoiceBox<String> _logSelector;

    public Sidebar() {
        _root = new VBox();
        _logSelector = new ChoiceBox<>();
        _root.setPrefSize(UIConstants.SIDEBAR_WIDTH, UIConstants.WINDOW_HEIGHT);
        _root.setBackground(Background.fill(Color.LIGHTGRAY));
        _root.setSpacing(UIConstants.SIDEBAR_SPACING);
        _root.setPadding(UIConstants.SIDEBAR_PADDING);
    }

    public void setLogSize(int perspectives){
        _logSelector.getItems().clear();
        for(int i = 0; i < perspectives; i++){
            _logSelector.getItems().add("Perspective " + i);
        }
        _logSelector.getSelectionModel().select(0);

        _logSelector.setOnAction(actionEvent -> {
            String perspective = _logSelector.getValue().split(" ")[1];
            LogViewer.getInstance().changeLogIndex(Integer.parseInt(perspective));
        });

        _logSelector.setPrefWidth(UIConstants.SIDEBAR_WIDTH);
        _root.getChildren().add(_logSelector);
    }

    public void updateLogDetails(Log log) {
        _root.getChildren().removeIf(node -> node instanceof Label);

        _root.getChildren().addAll(log.getLabels());
    }

    public VBox getRoot() {
        return _root;
    }
}
