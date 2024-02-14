package com.example.newcomertoolbox.logic;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class IndexLogic {
    public record IndexViewConfig(
            String img, String name, String path, boolean isCmd, String cmd
    ) {}

    public List<IndexViewConfig> readConfig() throws IOException {
        // 判断 ./config/index.setting 是否存在
        Path path = Path.of("config/indexViewConfig.json");
        boolean exists = Files.exists(path);
        if(!exists) {
            Files.createFile(path);
            // 写入一个空数组
            Files.writeString(path, "[]");
        }
        // 使用fastJson解析文件
        List<IndexViewConfig> indexViewConfigs;
        indexViewConfigs = JSON.parseArray(Files.readString(path), IndexViewConfig.class);
        return indexViewConfigs;
    }
}
