package org.llan.loggedrl.logviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.llan.loggedrl.logviewer.data.LogParser;
import org.llan.loggedrl.logviewer.data.PlayerLog;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("index.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        List<String> logLines = LogParser.parseLogFile("log.txt");
        System.out.println(logLines.get(2));
        PlayerLog log1 = new PlayerLog();
        log1.parse(logLines.subList(0, 4));
        System.out.println(log1);
    }

    public static void main(String[] args) {
        launch();
    }
}