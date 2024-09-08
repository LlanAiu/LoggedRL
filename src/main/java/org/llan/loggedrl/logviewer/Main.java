package org.llan.loggedrl.logviewer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.llan.loggedrl.logviewer.ui.LogViewer;
import org.llan.loggedrl.logviewer.ui.UIConstants;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(LogViewer.getInstance().getRoot(), UIConstants.WINDOW_WIDTH, UIConstants.WINDOW_HEIGHT);
        LogViewer.getInstance().setStageReference(stage);
        stage.setTitle("Log Viewer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}