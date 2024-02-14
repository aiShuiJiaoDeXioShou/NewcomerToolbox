package com.example.newcomertoolbox.view.sqltoword;

import com.example.newcomertoolbox.alert.SuccessAlert;
import com.example.newcomertoolbox.alert.WarningAlert;
import com.example.newcomertoolbox.logic.SqlToWord;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SqltoWordVM {

    private StringProperty dataUrl = new SimpleStringProperty();
    private StringProperty dataUserName = new SimpleStringProperty();
    private StringProperty dataPassword = new SimpleStringProperty();
    private StringProperty basePath = new SimpleStringProperty();
    private StringProperty databaseComboBox = new SimpleStringProperty();
    private SqlToWord sqlToWord;
    private ObjectProperty<ObservableList<String>> databaseComboBoxItems = new SimpleObjectProperty<>();
    private Property<ObservableList<String>> fileList = new SimpleObjectProperty<>();

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

    public Property<ObservableList<String>> databaseComboBoxItemsProperty() {
        return databaseComboBoxItems;
    }

    public void conn() {
        sqlToWord = new SqlToWord(
                dataUrl.get(),
                dataUserName.get(),
                dataPassword.get()
        );
        List<String> databaseNames = sqlToWord.getDatabaseNames();
        if (databaseNames == null) {
            WarningAlert.showWarning("错误", "错误", "连接数据库失败");
            return;
        }
        SuccessAlert.showSuccess("成功", "成功", "连接数据库成功");
        databaseComboBoxItems.setValue(FXCollections.observableArrayList(databaseNames));
        databaseComboBox.setValue(databaseNames.get(0));
    }

    public void ok() {
        if (sqlToWord == null) {
            WarningAlert.showWarning("错误", "错误", "请先连接数据库");
            return;
        }
        String database = databaseComboBox.get();
        sqlToWord.sqlToWord(basePath.get() + database + ".docx", database);
        try {
            fileList.setValue(FXCollections.observableArrayList(
                    Files.list(Path.of(basePath.get()))
                            .filter(path -> path.toString().endsWith(".docx"))
                            .map(Path::getFileName)
                            .map(Path::toString)
                            .toList()
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Property<ObservableList<String>> fileListProperty() {
        return fileList;
    }

    record SqltoWordSetting(String dataUrl, String dataUserName, String dataPassword, String basePath,
                            String databaseComboBox) {
    }

    public boolean saveSetting() {
        SqltoWordSetting sqltoWordSetting = new SqltoWordSetting(
                dataUrl.get(),
                dataUserName.get(),
                dataPassword.get(),
                basePath.get(),
                databaseComboBox.get()
        );
        // 保存为 sqltoWordSetting.txt 文件储存在当前目录下
        boolean exists = Files.exists(Path.of("sqltoWordSetting.txt"));
        if (!exists) {
            try {
                Files.createFile(Path.of("sqltoWordSetting.txt"));
                writeSetting(sqltoWordSetting);
            } catch (Exception e) {
                return false;
            }
        }
        // 覆盖
        else {
            writeSetting(sqltoWordSetting);
        }
        SuccessAlert.showSuccess("配置保存", "成功", "保存成功");
        return true;
    }

    private void writeSetting(SqltoWordSetting sqltoWordSetting) {
        try {
            Files.writeString(
                    Path.of("sqltoWordSetting.txt"),
                    """
                    dataUrl=%s
                    dataUserName=%s
                    dataPassword=%s
                    basePath=%s
                    databaseComboBox=%s
                    """.formatted(
                            sqltoWordSetting.dataUrl(),
                            sqltoWordSetting.dataUserName(),
                            sqltoWordSetting.dataPassword(),
                            sqltoWordSetting.basePath(),
                            sqltoWordSetting.databaseComboBox())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initSetting() {
        // 读取 sqltoWordSetting.txt 文件 该文件类似ini
        boolean exists = Files.exists(Path.of("sqltoWordSetting.txt"));
        if (!exists) {
            return;
        }
        try {
            String s = Files.readString(Path.of("sqltoWordSetting.txt"));
            String[] split = s.split("\n");
            for (String s1 : split) {
                // 截取第一个等号开头的
                String key = s1.substring(0, s1.indexOf("="));
                String value = s1.substring(s1.indexOf("=") + 1);
                if (key.isEmpty() || value.isEmpty()) {
                    continue;
                }
                switch (key) {
                    // 截取第一个等号之后的字符串
                    case "dataUrl" -> dataUrl.set(value);
                    case "dataUserName" -> dataUserName.set(value);
                    case "dataPassword" -> dataPassword.set(value);
                    case "basePath" -> basePath.set(value);
                    case "databaseComboBox" -> databaseComboBox.set(value);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // 获取生成的文件列表
        try {
            fileList.setValue(FXCollections.observableArrayList(
                    Files.list(Path.of(basePath.get()))
                            .filter(path -> path.toString().endsWith(".docx"))
                            .map(Path::getFileName)
                            .map(Path::toString)
                            .toList()
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
