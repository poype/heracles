package com.poype.heracles.core.domain.service;

import com.poype.heracles.core.domain.model.Environment;

import java.util.List;

public interface EnvironmentService {

    /**
     * 增加新的环境
     * @param envName 环境名称
     * @param envType 环境类型
     * @param defaultCpuOfApp 应用默认cpu配置
     * @param defaultMemoryOfApp 应用默认内存配置
     */
    void addNewEnvironment(String envName, String envType, int defaultCpuOfApp, int defaultMemoryOfApp);

    /**
     * 查询所有的环境列表
     * @return 环境模型列表
     */
    List<Environment> queryAllEnvironment();

    /**
     * 更新环境参数
     * @param envId 环境Id
     * @param defaultCpuOfApp 默认cpu配置
     * @param defaultMemoryOfApp 默认内存配置
     */
    void updateEnvironment(int envId, int defaultCpuOfApp, int defaultMemoryOfApp);
}
