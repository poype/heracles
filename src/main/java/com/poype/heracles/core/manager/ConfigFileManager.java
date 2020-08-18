package com.poype.heracles.core.manager;

public interface ConfigFileManager {

    /**
     * 为应用创建运维文件
     * @param appId 应用Id
     */
    void generateConfigFileForApp(String appId);

}
