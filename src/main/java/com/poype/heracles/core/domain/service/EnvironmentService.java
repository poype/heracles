package com.poype.heracles.core.domain.service;

public interface EnvironmentService {

    /**
     * 增加新的环境
     * @param envName 环境名称
     * @param envType 环境类型
     * @param defaultCpuOfApp 应用默认cpu配置
     * @param defaultMemoryOfApp 应用默认内存配置
     */
    void addNewEnvironment(String envName, String envType, int defaultCpuOfApp, int defaultMemoryOfApp);
}
