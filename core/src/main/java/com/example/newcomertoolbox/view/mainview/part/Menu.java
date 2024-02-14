package com.example.newcomertoolbox.view.mainview.part;

import com.example.newcomertoolbox.comm.Config;
import com.example.newcomertoolbox.router.Router;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class Menu {
    @FXML
    private Pane root;
    @FXML
    private ImageView labelImage;
    @FXML
    private Label menuLabel;
    private Router router;
    private String path;
    private String title;

    public Pane padding(String title, String path, String imageUrl, Router router) {
        this.title = title;
        this.path = path;
        this.router = router;
        menuLabel.setText(title);
        if (imageUrl != null) {
            labelImage.setImage(new Image(imageUrl));
        }
        root.setOnMouseClicked(e-> {
            active();
            router.push(path);
        });
        root.setId(path);
        return this.root;
    }

    public void active() {
        FlowPane parent = (FlowPane)root.getParent();
        // 清除其他的子类的active class
        parent.getChildren().forEach(child-> child.getStyleClass().remove("menu_item_active"));
        // 为当前root添加active class
        root.getStyleClass().add("menu_item_active");
    }

    public Pane getRoot() {
        return root;
    }

    public static Menu build() {
        return Config.fxmlController("view/mainview/MenuItem.fxml");
    }
}
