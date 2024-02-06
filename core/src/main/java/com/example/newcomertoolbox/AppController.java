package com.example.newcomertoolbox;

import com.example.newcomertoolbox.comm.FxUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AppController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button hello;

    @FXML
    public void initialize() {
        FxUtils.from(hello)
                .setText("点击咯")
                .bootstrapfx()
                .btnPrimary()
                .btnLg()
                .end()
                .node();
    }

    @FXML
    protected void onHelloButtonClick() {
        FxUtils.from(welcomeText)
                // 设置样式
                .bootstrapfx().h1().textSuccess().bgWarning().end()
                // 设置提示
                .setTooltip("Hello, World!")
                // 设置文本
                .setText("Hello World!")
                .node();
    }
}
