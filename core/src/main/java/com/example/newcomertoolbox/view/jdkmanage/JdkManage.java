package com.example.newcomertoolbox.view.jdkmanage;

import com.example.newcomertoolbox.comm.Config;
import javafx.scene.Parent;

public class JdkManage {

    public static Parent build() {
        return Config.fxmlNode("view/jdkmanage/JdkManageView.fxml");
    }

}
