module web.balneariotorreonapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires jdk.jsobject;
    requires com.google.gson;
    requires java.json;

    // permite que javafx.web acceda al paquete donde está HelloController
    opens web.balneariotorreonapp.ui to javafx.web;

    exports web.balneariotorreonapp.ui;
    exports web.balneariotorreonapp;
    opens web.balneariotorreonapp to javafx.web;
}
