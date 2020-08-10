package com.poype.heracles.core.manager;

import com.poype.heracles.core.domain.model.application.Application;

import java.util.List;

public interface ApplicationManager {

    /**
     * 增加应用基本信息
     * @param appName 应用名称
     * @param appType 应用类型
     * @param description 应用描述
     * @param devOwner 开发负责人
     * @param qaOwner 测试负责人
     * @param belongSystem 所属子系统
     * @param belongBusiness 所属业务
     * @param codeRepository 代码仓库地址
     * @param hostConfigNames 第三方域名配置名
     */
    String addApplicationBasicInfo(String appName, String appType, String description, String devOwner,
                                   String qaOwner, String belongSystem, String belongBusiness, String codeRepository,
                                   List<String> hostConfigNames);

    /**
     * 应用查询，既支持单个应用查询，也支持批量查询
     * @param appId appId
     * @param pageNum pageNum
     * @return 应用列表
     */
    List<Application> queryApplications(String appId, int pageNum);
}
