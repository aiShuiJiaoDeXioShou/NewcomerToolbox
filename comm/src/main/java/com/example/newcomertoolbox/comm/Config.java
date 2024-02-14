package com.example.newcomertoolbox.comm;

import com.sun.tools.javac.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Config {
    public static final String TITLE = "NewcomerToolbox";
    public static final int HEIGHT = 800;
    public static final int WIDTH = 1200;

    // 获取资源文件夹static中的图片路径
    public static String src(String path) {
        return Objects
                .requireNonNull(Main.class.getClassLoader().getResource(path))
                .toExternalForm();
    }

    public static <T extends Node> T fxmlNode(String path) {
        try {
            return FXMLLoader
                    .load(Objects
                            .requireNonNull(
                                    Main.class.getClassLoader().getResource(path)
                            )
                    );
        } catch (IOException e) {
            System.err.println("没有找到该类对应的FXML文件。"+path);
        }
        return null;
    }

    public static <T> T fxmlController(String path) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getClassLoader().getResource(path));
            fxmlLoader.load();
            return fxmlLoader.getController();
        } catch (IOException e) {
            System.err.println("没有找到该类对应的FXML文件。"+path);
        }
        return null;
    }

    // 获取程序的Stage
    public static Stage getStage(Pane root) {
        return (Stage) getScene(root).getWindow();
    }

    // 获取程序的Scene
    public static Scene getScene(Pane root) {
        return root.getScene();
    }
}
