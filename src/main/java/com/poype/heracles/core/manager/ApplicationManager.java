package com.poype.heracles.core.manager;

import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.dto.JavaApplicationDto;
import com.poype.heracles.core.domain.model.dto.SimpleApplicationDto;

import java.util.List;

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
    Application queryApplicationDetailById(String appId);

    /**
     * 分页查询应用的简要信息
     * @return 分页号
     */
    List<SimpleApplicationDto> querySimpleAppList(int pageNum);
}
