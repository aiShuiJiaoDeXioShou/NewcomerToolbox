package com.example.newcomertoolbox.alert;

import com.example.newcomertoolbox.comm.Config;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SuccessAlert extends Alert {
    public SuccessAlert(String title, String headerText, String contentText) {
        super(AlertType.INFORMATION);
        this.setTitle(title);
        this.setHeaderText(headerText);
        this.setContentText(contentText);
        // 设置窗体图标
        Stage window = (Stage)this.getDialogPane().getScene().getWindow();
        window.getIcons().add(new ImageView(Config.src("static/image/Tools.png")).getImage());
    }

    public static void showSuccess(String title, String headerText, String contentText) {
        SuccessAlert successAlert = new SuccessAlert(title, headerText, contentText);
        successAlert.showAndWait();
    }
}
