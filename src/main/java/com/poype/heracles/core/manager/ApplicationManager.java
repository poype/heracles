package com.poype.heracles.core.manager;

import com.poype.heracles.core.domain.model.dto.JavaApplicationDto;
import com.poype.heracles.core.facade.request.AddApplicationRequest;

import java.util.List;
import java.util.Map;

public interface ApplicationManager {

    /**
     * 增加应用基本信息
     * @param domainId 域Id
     * @param appName 应用名称
     * @param appType 应用类型
     * @param description 应用描述
     * @param devOwner 开发负责人
     * @param qaOwner 测试负责人
     * @param belongSystem 所属子系统
     * @param belongBusiness 所属业务
     * @param codeRepository 代码仓库地址
     * @param javaAppInfo Java应用额外信息
     */
    String createNewApplication(String domainId, String appName, String appType, String description, String devOwner,
                                List<String> devList, String qaOwner, List<String> qaList, String belongSystem,
                                String belongBusiness, String codeRepository, JavaApplicationDto javaAppInfo);

    /**
     * 单个应用详情查询
     * @param appId appId
     * @return 应用列表
     */
    AddApplicationRequest queryApplicationDetailById(String appId);

    /**
     * 分页查询应用的简要信息
     * @return 分页号
     */
    Map<String, Object> querySimpleAppList(int pageNum);

    /**
     * 更新应用的Java部分信息
     * @param appId 应用Id
     * @param javaAppInfo java部分信息
     */
    void updateJavaAppInfo(String appId, JavaApplicationDto javaAppInfo);

    /**
     * 查询所有应用的名字
     */
    List<String> queryAllNames();
}
