package com.example.newcomertoolbox.alert;

import com.example.newcomertoolbox.comm.Config;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class WarningAlert extends Alert {

    public WarningAlert(String title, String header, String content) {
        super(AlertType.WARNING);
        this.setTitle(title);
        this.setHeaderText(header);
        this.setContentText(content);
        // 设置窗体图标
        Stage window = (Stage)this.getDialogPane().getScene().getWindow();
        window.getIcons().add(new ImageView(Config.src("static/image/Tools.png")).getImage());
    }

    public static void showWarning(String title, String header, String content) {
        new WarningAlert(title, header, content).showAndWait();
    }
}
