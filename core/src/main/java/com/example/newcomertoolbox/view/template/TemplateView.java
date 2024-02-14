package com.example.newcomertoolbox.view.template;

import com.example.newcomertoolbox.comm.Config;
import javafx.scene.Parent;

public class TemplateView {
    public static Parent build() {
        return Config.fxmlNode("view/templateview/TemplateView.fxml");
    }
}
