package com.poype.heracles.core.manager;

import java.util.List;

public interface ConfigFileManager {

    /**
     * 为应用创建运维文件
     * @param appId 应用Id
     */
    void generateConfigFileForApp(String appId);

    List<String> queryAllHostNames();

}
