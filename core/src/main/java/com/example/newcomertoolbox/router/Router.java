package com.example.newcomertoolbox.router;

import com.example.newcomertoolbox.logic.BaseView;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Router extends Pane {
    private Map<String, Node> routes;
    // 历史记录数组
    private List<String> history = new ArrayList<>();
    private String nowRouter = "";

    public Router(@NotNull Map<String, Node> routes) {
        this.routes = routes;

    }

    public void push(String path) {
        Node view = routes.get(path);
        if (view != null && !path.equals(nowRouter)) {
            getChildren().clear();
            // view binding root
            if (view instanceof Pane pane) {
                pane.prefWidthProperty().bind(widthProperty());
                pane.prefHeightProperty().bind(heightProperty());
            }
            getChildren().add(view);
            this.nowRouter = path;
            history.add(path);
        }
    }

    public void add(String path, BaseView view) {
        routes.put(path, view);
    }

    public void remove(String path) {
        routes.remove(path);
    }

    public void back() {
        getChildren().clear();
        String prePath = history.get(history.size() - 1);
        push(prePath);
    }

    public String getNowRouter() {
        return nowRouter;
    }
}
