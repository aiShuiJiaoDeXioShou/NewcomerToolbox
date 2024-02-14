package com.example.newcomertoolbox.view.indexview;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.example.newcomertoolbox.alert.HintUtils;
import com.example.newcomertoolbox.comm.Config;
import com.example.newcomertoolbox.comm.FxUtils;
import com.example.newcomertoolbox.logic.IndexLogic;
import com.example.newcomertoolbox.task.AppTask;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IndexView {
    @FXML
    private BorderPane root;

    @FXML
    private Button add;

    @FXML
    private Button del;

    @FXML
    private FlowPane appRoot;

    private IndexVM indexVM;

    private Dialog<IndexLogic.IndexViewConfig> addDialog;

    @FXML
    public void initialize() {
        indexVM = new IndexVM();
        addDialog = createIndexViewConfigDialog();
        appRoot.setHgap(10);
        appRoot.setHgap(10);
        indexVM.getIndexViewConfigs().forEach(i-> appRoot.getChildren().add(buildMenu(i)));
        add.setOnAction(event -> addDialog.showAndWait().ifPresent(indexViewConfig -> {
            indexVM.getIndexViewConfigs().add(indexViewConfig);
            appRoot.getChildren().add(buildMenu(indexViewConfig));
        }));
        del.setOnAction(event -> {
            if (appRoot.getChildren().size() > 0) {
                appRoot.getChildren().remove(appRoot.getChildren().size() - 1);
                indexVM.getIndexViewConfigs().remove(indexVM.getIndexViewConfigs().size() - 1);
            }
        });

        // 关闭时保存配置文件
        AppTask.add(AppTask.TaskEnum.END, ()-> {
            try {
                Path path = Path.of("config/indexViewConfig.json");
                String json = JSON.toJSONString(indexVM.getIndexViewConfigs());
                Files.writeString(path, json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public Dialog<IndexLogic.IndexViewConfig> createIndexViewConfigDialog() {
        Dialog<IndexLogic.IndexViewConfig> dialog = new Dialog<>();
        dialog.getDialogPane().setPrefSize(600,600);
        dialog.setTitle("创建快捷应用");
        Config.getStage(dialog.getDialogPane())
                .getIcons().add(new Image(Config.src("static/image/Tools.png")));

        TextField imgField = new TextField();
        TextField nameField = new TextField();
        TextField pathField = new TextField();
        CheckBox isCmdCheckBox = new CheckBox();
        TextField cmdField = new TextField();

        Button imgButton = new Button("选择图标");
        imgButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("选择应用图标");
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                imgField.setText(file.getAbsolutePath());
            }
        });

        Button pathButton = new Button("选择路径");
        pathButton.setOnAction(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("选择应用路径");
            File directory = directoryChooser.showDialog(null);
            if (directory != null) {
                pathField.setText(directory.getAbsolutePath());
            }
        });

        GridPane grid = new GridPane();
        grid.add(new Label("应用图标:"), 0, 0);
        grid.add(imgField, 1, 0);
        grid.add(imgButton, 2, 0);
        grid.add(new Label("应用别名:"), 0, 1);
        grid.add(nameField, 1, 1);
        grid.add(new Label("应用路径:"), 0, 2);
        grid.add(pathField, 1, 2);
        grid.add(pathButton, 2, 2);
        grid.add(new Label("是否为命令行:"), 0, 3);
        grid.add(isCmdCheckBox, 1, 3);
        grid.add(new Label("具体指令:"), 0, 4);
        grid.add(cmdField, 1, 4);
        dialog.getDialogPane().setContent(grid);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        ButtonType submitButtonType = new ButtonType("创建", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(submitButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == submitButtonType) {
                return new IndexLogic.IndexViewConfig(
                        imgField.getText(),
                        nameField.getText(),
                        pathField.getText(),
                        isCmdCheckBox.isSelected(),
                        cmdField.getText()
                );
            }
            return null;
        });

        return dialog;
    }

    public VBox buildMenu(IndexLogic.IndexViewConfig config) {
        return FxUtils.from(new VBox())
                .addStyle("-fx-spacing: 10;")
                .addStyle("-fx-alignment: center;")
                // 添加阴影
                .addStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 10, 0, 0, 0);")
                // 添加圆角
                .addStyle("-fx-background-radius: 10;")
                // 设置padding
                .addStyle("-fx-padding: 5;")
                // 添加背景色
                .addStyle("-fx-background-color: #f4f4f4;")
                .addChildren(()-> {
                    if (!StrUtil.isBlank(config.img()) && FileUtil.exist(config.img())) {
                        return FxUtils.from(new ImageView(new Image(config.img())))
                                .workpiece(self-> {
                                    // 设置图像大小
                                    self.setFitHeight(50);
                                    self.setFitWidth(50);
                                })
                                .node();
                    } else {
                        // 返回icon.png
                        return FxUtils.from(new ImageView(Config.src("static/image/icon.png")))
                                .setSize(50, 50)
                                .workpiece(self-> {
                                    // 设置图像大小
                                    self.setFitHeight(50);
                                    self.setFitWidth(50);
                                })
                                .node();
                    }
                })
                .addChildren(new Label(config.name()))
                .click(event -> {
                    if (config.isCmd()) {
                        RuntimeUtil.exec(config.cmd());
                        return;
                    }
                    // 打开指定path路径的应用
                    try {
                        Desktop.getDesktop().open(new File(config.path()));
                    } catch (IOException e) {
                        HintUtils.popup("错误", "打开应用失败", "请检查应用位置是否可靠！");
                        e.printStackTrace();
                    }
                })
                .node();
    }

    public static Pane build() {
        IndexView indexView = Config.fxmlController("view/indexview/IndexView.fxml");
        return indexView.getRoot();
    }

    public BorderPane getRoot() {
        return root;
    }
}
