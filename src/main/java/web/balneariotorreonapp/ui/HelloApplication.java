package web.balneariotorreonapp.ui;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import web.balneariotorreonapp.HelloController;

import java.net.URL;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                webEngine.executeScript("document.body.style.overflow='auto';");
            }
        });


        URL url = getClass().getResource("/web/balneariotorreonapp/index.html");
        if (url != null) {
            webEngine.load(url.toExternalForm());
        } else {
            System.err.println("No se encontró el index.html en resources");
            webEngine.loadContent("<h1 style='color:red'>Error: No se encontró index.html</h1>");
        }

        HelloController controller = new HelloController(webEngine);

        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("javaApp", controller);
            }
        });

        Scene scene = new Scene(webView, 1100, 700);
        stage.setTitle("BalnearioTorreon - Gestion");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.show();


        scene.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });
    }

    public static void main(String[] args) {
        System.setProperty("prism.lcdtext", "true");
        System.setProperty("prism.text", "t2k");
        System.setProperty("prism.subpixeltext", "true");
        System.setProperty("glass.win.uiScale", "100%");
        System.setProperty("prism.allowhidpi", "false");
        System.setProperty("prism.order", "d3d,es2,sw");
        System.setProperty("prism.antialiasing", "true");
        launch();
    }
}
