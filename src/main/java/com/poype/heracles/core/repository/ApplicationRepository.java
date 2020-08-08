package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.application.Application;

import java.util.List;

public interface ApplicationRepository {

    /**
     * 添加应用基本信息
     * @param application 应用模型
     */
    void addApplicationBasicInfo(Application application);

    /**
     * 根据应用名字查询应用模型
     * @param appName 应用名字
     * @return 对应的应用模型
     */
    Application queryByAppName(String appName);
}
