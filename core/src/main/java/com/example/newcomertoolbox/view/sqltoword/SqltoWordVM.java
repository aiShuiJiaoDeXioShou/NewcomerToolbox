package com.example.newcomertoolbox.view.sqltoword;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MultiwordVM {

    private StringProperty dataUrl = new SimpleStringProperty();
    private StringProperty dataUserName = new SimpleStringProperty();
    private StringProperty dataPassword = new SimpleStringProperty();
    private StringProperty basePath = new SimpleStringProperty();
    private StringProperty databaseComboBox = new SimpleStringProperty();

    public MultiwordVM() {
    }

    public String getDataUrl() {
        return dataUrl.get();
    }

    public StringProperty dataUrlProperty() {
        return dataUrl;
    }

    public String getDataUserName() {
        return dataUserName.get();
    }

    public StringProperty dataUserNameProperty() {
        return dataUserName;
    }

    public String getDataPassword() {
        return dataPassword.get();
    }

    public StringProperty dataPasswordProperty() {
        return dataPassword;
    }

    public String getBasePath() {
        return basePath.get();
    }

    public StringProperty basePathProperty() {
        return basePath;
    }

    public String getDatabaseComboBox() {
        return databaseComboBox.get();
    }

    public StringProperty databaseComboBoxProperty() {
        return databaseComboBox;
    }
}
