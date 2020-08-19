package com.poype.heracles.core.domain.service;

import com.poype.heracles.core.domain.model.dto.JavaApplicationDto;

import java.util.List;

public interface ApplicationService {

    /**
     * 创建一个新的应用及配置
     * @param appName 应用名称
     * @param appType 应用类型
     * @param description 应用描述
     * @param devOwner 研发owner
     * @param qaOwner 测试owner
     * @param belongSystem 所属系统
     * @param belongBusiness 所属业务
     * @param codeRepository 代码仓库
     * @param javaAppInfo java应用信息
     * @return 应用Id
     */
    String createNewApplication(String domainId, String appName, String appType, String description, String devOwner,
                                List<String> devList, String qaOwner, List<String> qaList, String belongSystem,
                                String belongBusiness, String codeRepository, JavaApplicationDto javaAppInfo);

    /**
     * 更新Java应用信息
     * @param appId 应用Id
     * @param javaAppInfo Java信息
     */
    void updateJavaAppInfo(String appId, JavaApplicationDto javaAppInfo);
}
