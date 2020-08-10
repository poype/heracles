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

    /**
     * 根据应用Id查询应用列表
     * @param appId 应用Id
     * @return 应用对象
     */
    Application queryByAppId(String appId);

    /**
     * 查询一页记录
     * @param pageNum 页号
     * @return 应用列表
     */
    List<Application> queryPage(int pageNum);
}
