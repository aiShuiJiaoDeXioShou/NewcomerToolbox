package com.example.newcomertoolbox.view.mainview;

import cn.hutool.setting.Setting;
import com.example.newcomertoolbox.comm.Config;
import com.example.newcomertoolbox.router.Router;
import com.example.newcomertoolbox.router.RouterInstance;
import com.example.newcomertoolbox.task.AppTask;
import com.example.newcomertoolbox.view.mainview.part.Menu;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MainView {
    protected Router router = RouterInstance.getInstance();

    @FXML
    private BorderPane root;
    @FXML
    private FlowPane menusBox;
    private Setting config;

    // 初始化配置文件类
    private void initConfig() {
        // 初始化配置文件, 使用hutool配置文件类
        // 判断MainView.setting是否存在
        Path path = Path.of("MainView.setting");
        if (!Files.exists(path)) {
            try {
                // 创建并写入内容
                Files.createFile(path);
                Files.writeString(path, "activeRouter=/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = new Setting(path.toAbsolutePath().toString());
    }

    @FXML
    public void initialize() {
        initConfig();
        Menu indexMenu = Menu.build();
        Pane index = indexMenu.padding(
                        "首页", "/",
                        Config.src("static/image/tool.png"),
                        router);
        Pane word = Menu.build().padding(
                "Sql转Word", "/sqltoword",
                Config.src("static/image/sql.png"),
                router);
        Pane jdk = Menu.build().padding(
                "jdk管理", "/jdkmanage",
                Config.src("static/image/java.png"),
                router);
        Pane template = Menu.build().padding(
                "template", "/template",
                Config.src("static/image/Template.png"),
                router);

        Pane bill = Menu.build().padding(
                "bill", "/bill",
                Config.src("static/image/bill.png"),
                router);

        menusBox.getChildren().addAll(index, word, jdk,template,bill);
        menusBox.getChildren().addAll();
        root.setCenter(router);
        // 设置第一个菜单默认选中
        router.push(config.getStr("activeRouter"));

        menusBox.getChildren()
                .forEach(menu -> {
                    if (menu.getId().equals(config.getStr("activeRouter"))) {
                        menu.getStyleClass().add("menu_item_active");
                    } else {
                        menu.getStyleClass().remove("menu_item_active");
                    }
                });

        AppTask.add(AppTask.TaskEnum.END, () -> {
            config.set("activeRouter", router.getNowRouter());
            config.store();
        });
    }

    public static BorderPane build() {
        return Config.fxmlNode("view/mainview/MainView.fxml");
    }
}
