package com.example.newcomertoolbox.comm;

import cn.hutool.core.lang.func.Func0;
import cn.hutool.core.lang.func.VoidFunc1;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Tooltip;

import java.util.function.Function;

public class FxUtils<T extends Node> {
    private T node;

    public static <T extends Node> FxUtils<T> from(T node) {
        FxUtils<T> instance = new FxUtils<>();
        instance.node = node;
        return instance;
    }

    public FxUtils<T> addStyleClass(String clazz) {
        node.getStyleClass().add(clazz);
        return this;
    }

    public FxUtils<T> workpiece(VoidFunc1<T> func) {
        try {
            func.call(node);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public FxUtils<T> setSize(double width, double height) {
        if (node instanceof Region) {
            Region region = (Region) node;
            region.setPrefWidth(width);
            region.setPrefHeight(height);
        }
        return this;
    }

    public FxUtils<T> setText(String text) {
        if (node instanceof Label) {
            ((Label) node).setText(text);
        }
        return this;
    }

    public FxUtils<Circle> setRadius(double radius) {
        if (node instanceof Circle) {
            ((Circle) node).setRadius(radius);
        }
        return (FxUtils<Circle>) this;
    }

    public FxUtils<Rectangle> setCornerRadius(double cornerRadius) {
        if (node instanceof Rectangle) {
            ((Rectangle) node).setArcWidth(cornerRadius);
            ((Rectangle) node).setArcHeight(cornerRadius);
        }
        return (FxUtils<Rectangle>) this;
    }

    public FxUtils<Polygon> setPolygonPoints(double... points) {
        if (node instanceof Polygon) {
            for (int i = 0; i < points.length; i++) {
                ((Polygon) node).getPoints().set(i, points[i]);
            }
        }
        return (FxUtils<Polygon>) this;
    }

    public FxUtils<T> setLayoutX(double x) {
        node.setLayoutX(x);
        return this;
    }

    public FxUtils<T> setLayoutY(double y) {
        node.setLayoutY(y);
        return this;
    }

    public FxUtils<T> setMargin(Node child, Insets insets) {
        HBox.setMargin(child, insets);
        return this;
    }

    public Scene scene() {
        return node.getScene();
    }

    public Stage stage() {
        return (Stage) scene().getWindow();
    }

    public FxUtils<T> setId(String id) {
        node.setId(id);
        return this;
    }

    public FxUtils<T> setStyle(String style) {
        node.setStyle(style);
        return this;
    }

    public FxUtils<T> addStyle(String style) {
        node.setStyle(node.getStyle() + style);
        return this;
    }


    public FxUtils<T> removeStyleClass(String clazz) {
        node.getStyleClass().remove(clazz);
        return this;
    }

    public FxUtils<T> toggleStyleClass(String clazz) {
        if (node.getStyleClass().contains(clazz)) {
            node.getStyleClass().remove(clazz);
        } else {
            node.getStyleClass().add(clazz);
        }
        return this;
    }

    public FxUtils<T> setStyleClass(String clazz) {
        node.getStyleClass().clear();
        node.getStyleClass().add(clazz);
        return this;
    }

    public FxUtils<T> setTooltip(String tooltipText) {
        Tooltip tooltip = new Tooltip(tooltipText);
        Tooltip.install(node, tooltip);
        return this;
    }

    public FxUtils<T> addChildren(Node... children) {
        if (node instanceof Pane) {
            ((Pane) node).getChildren().addAll(children);
        }
        return this;
    }

    public FxUtils<T> addChildren(Node children) {
        if (node instanceof Pane) {
            ((Pane) node).getChildren().add(children);
        }
        return this;
    }

    public FxUtils<T> addChildren(Func0<Node> runnable) {
        try {
            Node call = runnable.call();
            if (node instanceof Pane) {
                ((Pane) node).getChildren().add(call);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public FxUtils<T> click(EventHandler<? super MouseEvent> mouseEvent) {
        node.setOnMouseClicked(mouseEvent);
        return this;
    }

    public BootstrapFX<T> bootstrapfx() {
        return new BootstrapFX<>(this.node, this);
    }

    public T node() {
        return node;
    }
}
