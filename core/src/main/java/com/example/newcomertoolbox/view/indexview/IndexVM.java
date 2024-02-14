package com.example.newcomertoolbox.view.indexview;

import com.example.newcomertoolbox.alert.HintUtils;
import com.example.newcomertoolbox.logic.IndexLogic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IndexVM {
    private final IndexLogic logic = new IndexLogic();

    private ObjectProperty<ObservableList<IndexLogic.IndexViewConfig>> indexViewConfigs = new SimpleObjectProperty<>();

    public IndexVM() {
        try {
            indexViewConfigs.setValue(
                    FXCollections.observableArrayList(logic.readConfig())
            );
        } catch (Exception e) {
            HintUtils.popup("错误", "读取配置文件失败", "请检查是否存在相关读取权限！");
            e.printStackTrace();
        }
    }
    public ObservableList<IndexLogic.IndexViewConfig> getIndexViewConfigs() {
        return indexViewConfigs.getValue();
    }

    public ObjectProperty<ObservableList<IndexLogic.IndexViewConfig>> indexViewConfigsProperty() {
        return indexViewConfigs;
    }
}
