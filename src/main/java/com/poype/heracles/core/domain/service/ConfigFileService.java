package com.poype.heracles.core.domain.service;

import com.poype.heracles.core.domain.model.application.JavaApplication;

public interface ConfigFileService {

    /**
     * 为java应用生成配置文件
     * @param app java应用模型
     */
    void generateConfigFileForJavaApp(JavaApplication app);
}
