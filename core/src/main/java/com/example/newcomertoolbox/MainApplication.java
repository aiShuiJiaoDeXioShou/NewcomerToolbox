package com.example.newcomertoolbox;

import atlantafx.base.theme.PrimerLight;
import com.example.newcomertoolbox.comm.Config;
import com.example.newcomertoolbox.task.AppTask;
import com.example.newcomertoolbox.view.mainview.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void init() throws Exception {
        AppTask.run(AppTask.TaskEnum.START);
    }

    @Override
    public void start(Stage stage) throws IOException {
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        BorderPane root = MainView.build();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle(Config.TITLE);
        stage.getIcons().add(new Image(Config.src("static/image/Tools.png")));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        AppTask.run(AppTask.TaskEnum.END);
    }
}
