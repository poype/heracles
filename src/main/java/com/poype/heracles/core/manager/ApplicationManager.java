package com.poype.heracles.core.manager;

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
     * 确定git地址是否存在
     * @param url 代码url
     * @return 校验结果
     */
    void checkGitAddr(String url);
}
