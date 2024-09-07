module org.example.loggedrl {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    exports org.example.loggedrl.logviewer;
    opens org.example.loggedrl.logviewer to javafx.fxml;
}