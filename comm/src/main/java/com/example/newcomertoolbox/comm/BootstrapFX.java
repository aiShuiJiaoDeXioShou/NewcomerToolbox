package com.example.newcomertoolbox.comm;

import javafx.scene.Node;

public class BootstrapFX<T extends Node> {
    private T node;
    private FxUtils<T> fxUtils;
    public BootstrapFX(T node,FxUtils<T> fxUtils) {
        this.node = node;
        this.fxUtils = fxUtils;
    }

    public FxUtils<T> end() {
        return fxUtils;
    }

    public BootstrapFX<T> b() {
        node.getStyleClass().add("b");
        return this;
    }

    public BootstrapFX<T> strong() {
        node.getStyleClass().add("strong");
        return this;
    }

    public BootstrapFX<T> i() {
        node.getStyleClass().add("i");
        return this;
    }

    public BootstrapFX<T> em() {
        node.getStyleClass().add("em");
        return this;
    }

    public BootstrapFX<T> italic() {
        node.getStyleClass().add("italic");
        return this;
    }

    public BootstrapFX<T> dfn() {
        node.getStyleClass().add("dfn");
        return this;
    }

    public BootstrapFX<T> small() {
        node.getStyleClass().add("small");
        return this;
    }

    public BootstrapFX<T> code() {
        node.getStyleClass().add("code");
        return this;
    }

    public BootstrapFX<T> kbd() {
        node.getStyleClass().add("kbd");
        return this;
    }

    public BootstrapFX<T> pre() {
        node.getStyleClass().add("pre");
        return this;
    }

    public BootstrapFX<T> samp() {
        node.getStyleClass().add("samp");
        return this;
    }

    public BootstrapFX<T> h1() {
        node.getStyleClass().add("h1");
        return this;
    }

    public BootstrapFX<T> h2() {
        node.getStyleClass().add("h2");
        return this;
    }

    public BootstrapFX<T> h3() {
        node.getStyleClass().add("h3");
        return this;
    }

    public BootstrapFX<T> h4() {
        node.getStyleClass().add("h4");
        return this;
    }

    public BootstrapFX<T> h5() {
        node.getStyleClass().add("h5");
        return this;
    }

    public BootstrapFX<T> h6() {
        node.getStyleClass().add("h6");
        return this;
    }

    public BootstrapFX<T> lead() {
        node.getStyleClass().add("lead");
        return this;
    }

    public BootstrapFX<T> p() {
        node.getStyleClass().add("p");
        return this;
    }

    public BootstrapFX<T> textMute() {
        node.getStyleClass().add("text-mute");
        return this;
    }

    public BootstrapFX<T> textPrimary() {
        node.getStyleClass().add("text-primary");
        return this;
    }

    public BootstrapFX<T> textSuccess() {
        node.getStyleClass().add("text-success");
        return this;
    }

    public BootstrapFX<T> textInfo() {
        node.getStyleClass().add("text-info");
        return this;
    }

    public BootstrapFX<T> textWarning() {
        node.getStyleClass().add("text-warning");
        return this;
    }

    public BootstrapFX<T> textDanger() {
        node.getStyleClass().add("text-danger");
        return this;
    }

    public BootstrapFX<T> bgPrimary() {
        node.getStyleClass().add("bg-primary");
        return this;
    }

    public BootstrapFX<T> bgSuccess() {
        node.getStyleClass().add("bg-success");
        return this;
    }

    public BootstrapFX<T> bgInfo() {
        node.getStyleClass().add("bg-info");
        return this;
    }

    public BootstrapFX<T> bgWarning() {
        node.getStyleClass().add("bg-warning");
        return this;
    }

    public BootstrapFX<T> bgDanger() {
        node.getStyleClass().add("bg-danger");
        return this;
    }

    public BootstrapFX<T> btn() {
        node.getStyleClass().add("btn");
        return this;
    }

    public BootstrapFX<T> btnDefault() {
        node.getStyleClass().add("btn-default");
        return this;
    }

    public BootstrapFX<T> btnPrimary() {
        node.getStyleClass().add("btn-primary");
        return this;
    }

    public BootstrapFX<T> btnSuccess() {
        node.getStyleClass().add("btn-success");
        return this;
    }

    public BootstrapFX<T> btnInfo() {
        node.getStyleClass().add("btn-info");
        return this;
    }

    public BootstrapFX<T> btnWarning() {
        node.getStyleClass().add("btn-warning");
        return this;
    }

    public BootstrapFX<T> btnDanger() {
        node.getStyleClass().add("btn-danger");
        return this;
    }

    public BootstrapFX<T> btnLg() {
        node.getStyleClass().add("btn-lg");
        return this;
    }

    public BootstrapFX<T> btnSm() {
        node.getStyleClass().add("btn-sm");
        return this;
    }

    public BootstrapFX<T> btnXs() {
        node.getStyleClass().add("btn-xs");
        return this;
    }

    public BootstrapFX<T> splitMenuBtn() {
        node.getStyleClass().add("split-menu-btn");
        return this;
    }

    public BootstrapFX<T> splitMenuBtnDefault() {
        node.getStyleClass().add("split-menu-btn-default");
        return this;
    }

    public BootstrapFX<T> splitMenuBtnPrimary() {
        node.getStyleClass().add("split-menu-btn-primary");
        return this;
    }

    public BootstrapFX<T> splitMenuBtnSuccess() {
        node.getStyleClass().add("split-menu-btn-success");
        return this;
    }

    public BootstrapFX<T> splitMenuBtnInfo() {
        node.getStyleClass().add("split-menu-btn-info");
        return this;
    }

    public BootstrapFX<T> splitMenuBtnWarning() {
        node.getStyleClass().add("split-menu-btn-warning");
        return this;
    }

    public BootstrapFX<T> splitMenuBtnDanger() {
        node.getStyleClass().add("split-menu-btn-danger");
        return this;
    }

    public BootstrapFX<T> splitMenuBtnLg() {
        node.getStyleClass().add("split-menu-btn-lg");
        return this;
    }

    public BootstrapFX<T> splitMenuBtnSm() {
        node.getStyleClass().add("split-menu-btn-sm");
        return this;
    }

    public BootstrapFX<T> splitMenuBtnXs() {
        node.getStyleClass().add("split-menu-btn-xs");
        return this;
    }

    public BootstrapFX<T> lbl() {
        node.getStyleClass().add("lbl");
        return this;
    }

    public BootstrapFX<T> lblDefault() {
        node.getStyleClass().add("lbl-default");
        return this;
    }

    public BootstrapFX<T> lblPrimary() {
        node.getStyleClass().add("lbl-primary");
        return this;
    }

    public BootstrapFX<T> lblSuccess() {
        node.getStyleClass().add("lbl-success");
        return this;
    }

    public BootstrapFX<T> lblInfo() {
        node.getStyleClass().add("lbl-info");
        return this;
    }

    public BootstrapFX<T> lblWarning() {
        node.getStyleClass().add("lbl-warning");
        return this;
    }

    public BootstrapFX<T> lblDanger() {
        node.getStyleClass().add("lbl-danger");
        return this;
    }

    public BootstrapFX<T> panel() {
        node.getStyleClass().add("panel");
        return this;
    }

    public BootstrapFX<T> panelDefault() {
        node.getStyleClass().add("panel-default");
        return this;
    }

    public BootstrapFX<T> panelPrimary() {
        node.getStyleClass().add("panel-primary");
        return this;
    }

    public BootstrapFX<T> panelSuccess() {
        node.getStyleClass().add("panel-success");
        return this;
    }

    public BootstrapFX<T> panelInfo() {
        node.getStyleClass().add("panel-info");
        return this;
    }

    public BootstrapFX<T> panelWarning() {
        node.getStyleClass().add("panel-warning");
        return this;
    }

    public BootstrapFX<T> panelDanger() {
        node.getStyleClass().add("panel-danger");
        return this;
    }

    public BootstrapFX<T> panelHeading() {
        node.getStyleClass().add("panel-heading");
        return this;
    }

    public BootstrapFX<T> panelTitle() {
        node.getStyleClass().add("panel-title");
        return this;
    }

    public BootstrapFX<T> panelBody() {
        node.getStyleClass().add("panel-body");
        return this;
    }

    public BootstrapFX<T> panelFooter() {
        node.getStyleClass().add("panel-footer");
        return this;
    }

    public BootstrapFX<T> alert() {
        node.getStyleClass().add("alert");
        return this;
    }

    public BootstrapFX<T> alertSuccess() {
        node.getStyleClass().add("alert-success");
        return this;
    }

    public BootstrapFX<T> alertInfo() {
        node.getStyleClass().add("alert-info");
        return this;
    }

    public BootstrapFX<T> alertWarning() {
        node.getStyleClass().add("alert-warning");
        return this;
    }

    public BootstrapFX<T> alertDanger() {
        node.getStyleClass().add("alert-danger");
        return this;
    }

    public BootstrapFX<T> btnGroupHorizontal() {
        node.getStyleClass().add("btn-group-horizontal");
        return this;
    }

    public BootstrapFX<T> btnGroupVertical() {
        node.getStyleClass().add("btn-group-vertical");
        return this;
    }

    public BootstrapFX<T> progressBarPrimary() {
        node.getStyleClass().add("progress-bar-primary");
        return this;
    }

    public BootstrapFX<T> progressBarSuccess() {
        node.getStyleClass().add("progress-bar-success");
        return this;
    }

    public BootstrapFX<T> progressBarInfo() {
        node.getStyleClass().add("progress-bar-info");
        return this;
    }

    public BootstrapFX<T> progressBarWarning() {
        node.getStyleClass().add("progress-bar-warning");
        return this;
    }

    public BootstrapFX<T> progressBarDanger() {
        node.getStyleClass().add("progress-bar-danger");
        return this;
    }

    public BootstrapFX<T> tooltipPrimary() {
        node.getStyleClass().add("tooltip-primary");
        return this;
    }

    public BootstrapFX<T> tooltipSuccess() {
        node.getStyleClass().add("tooltip-success");
        return this;
    }

    public BootstrapFX<T> tooltipInfo() {
        node.getStyleClass().add("tooltip-info");
        return this;
    }

    public BootstrapFX<T> tooltipWarning() {
        node.getStyleClass().add("tooltip-warning");
        return this;
    }

    public BootstrapFX<T> tooltipDanger() {
        node.getStyleClass().add("tooltip-danger");
        return this;
    }

    public BootstrapFX<T> badge() {
        node.getStyleClass().add("badge");
        return this;
    }

}
