package com.example.newcomertoolbox.view.bill;

import com.example.newcomertoolbox.comm.Config;
import javafx.scene.Node;

public class BillView {

    public static Node build() {
        return Config.fxmlNode("view/bill/BillView.fxml");
    }

}
