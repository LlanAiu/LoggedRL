module org.llan.loggedrl {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    exports org.llan.loggedrl.logviewer;
    opens org.llan.loggedrl.logviewer to javafx.fxml;
}