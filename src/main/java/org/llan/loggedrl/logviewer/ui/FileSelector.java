package org.llan.loggedrl.logviewer.ui;

import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileSelector {
    private FileChooser _chooser;
    private MenuItem _upload;

    public FileSelector(Stage stage){
        _chooser = new FileChooser();
        _upload = new MenuItem("Upload");

        _upload.setOnAction(e -> {
            _chooser.setTitle("Open Log File");
            File file = _chooser.showOpenDialog(stage);
            if(file != null){
                LogViewer.getInstance().setLogs(file);
            }
        });
    }

    public MenuItem getMenuItem(){
        return _upload;
    }
}
