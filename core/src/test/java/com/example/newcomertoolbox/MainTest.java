package com.example.newcomertoolbox;

import com.example.newcomertoolbox.comm.Config;
import com.example.newcomertoolbox.view.mainview.part.Menu;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MainTest extends Application {

    @Test
    public void url() {
        String src = Config.src("view/mainview/MenuItem.fxml");
        System.out.println(src);
//        Node node = Config.fxmlNode("view/mainview/MenuItem.fxml");
//        System.out.println(node);
        Menu menu = Config.fxmlController("view/mainview/MenuItem.fxml");
        System.out.println(menu);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
