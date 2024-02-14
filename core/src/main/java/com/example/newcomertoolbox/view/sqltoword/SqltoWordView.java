package com.example.newcomertoolbox.view.sqltoword;

import com.example.newcomertoolbox.alert.WarningAlert;
import com.example.newcomertoolbox.comm.Config;
import com.example.newcomertoolbox.logic.BaseView;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SqltoWordView extends BaseView {
    @FXML
    private TextField dataUrl;
    @FXML
    private TextField dataUserName;
    @FXML
    private TextField dataPassword;
    @FXML
    private Button conn;
    @FXML
    private TextField basePath;
    @FXML
    private ComboBox<String> databaseComboBox;
    @FXML
    private Button ok;
    @FXML
    private Button saveSetting;
    @FXML
    private ListView<String> fileList;

    @FXML
    public void initialize() {
        SqltoWordVM sqltoWordVM = new SqltoWordVM();
        dataUrl.textProperty().bindBidirectional(sqltoWordVM.dataUrlProperty());
        dataUserName.textProperty().bindBidirectional(sqltoWordVM.dataUserNameProperty());
        dataPassword.textProperty().bindBidirectional(sqltoWordVM.dataPasswordProperty());
        basePath.textProperty().bindBidirectional(sqltoWordVM.basePathProperty());
        databaseComboBox.valueProperty().bindBidirectional(sqltoWordVM.databaseComboBoxProperty());
        databaseComboBox.itemsProperty().bindBidirectional(sqltoWordVM.databaseComboBoxItemsProperty());
        fileList.itemsProperty().bindBidirectional(sqltoWordVM.fileListProperty());
        conn.setOnAction(event -> sqltoWordVM.conn());
        ok.setOnAction(event -> sqltoWordVM.ok());
        // 为fileList每个item添加双击事件
        fileList.setOnMouseClicked(fileListDoubleClick());
        saveSetting.setOnAction(event -> {
            boolean b = sqltoWordVM.saveSetting();
            if (!b) {
                WarningAlert.showWarning("保存失败", "保存失败", "保存失败");
            }
        });
        // 为fileList添加右键菜单
        fileList.setContextMenu(buildContextMenu());
        sqltoWordVM.initSetting();
    }

    private ContextMenu buildContextMenu() {
        ContextMenu contextMenu = new ContextMenu();
        // 删除
        MenuItem delete = new MenuItem("删除");
        delete.setOnAction(event -> {
            String selectedItem = fileList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try {
                    Files.delete(Path.of(selectedItem));
                } catch (IOException e) {
                    WarningAlert.showWarning("删除失败", "删除失败", "删除失败");
                }
            }
        });
        // 打开到文件夹
        MenuItem openFolder = new MenuItem("打开到文件夹");
        openFolder.setOnAction(event -> {
            String selectedItem = fileList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                try {
                    Runtime.getRuntime().exec("cmd /c start explorer /select," + selectedItem);
                } catch (IOException e) {
                    WarningAlert.showWarning("打开文件夹失败", "打开文件夹失败", "打开文件夹失败");
                }
            }
        });
        contextMenu.getItems().addAll(delete, openFolder);
        return contextMenu;
    }

    @NotNull
    private EventHandler<MouseEvent> fileListDoubleClick() {
        return event -> {
            if (event.getClickCount() == 2) {
                String selectedItem = fileList.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    try {
                        Runtime.getRuntime().exec("cmd /c start " + selectedItem);
                    } catch (IOException e) {
                        WarningAlert.showWarning("打开文件失败", "打开文件失败", "没有找到该文件的默认打开程序!");
                    }
                }
            }
        };
    }

    public static AnchorPane build() {
        return Config.fxmlNode("view/sqltowordview/SqltoWordView.fxml");
    }
}
