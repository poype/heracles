package com.poype.heracles.core.domain.model;

/**
 * 应用
 */
public class Application {

    /**
     * 应用Id
     */
    private String applicationId;

    /**
     * 应用名字
     */
    private String applicationName;

    /**
     * 应用类型
     */
    private String applicationType;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 代码仓库
     */
    private String codeRepository;

    /**
     * 研发owner
     */
    private String devOwner;

    /**
     * 测试owner
     */
    private String qaOwner;

    /**
     * 应用配置信息
     */
    private ApplicationConfig config;

    /**
     * 所属子系统
     */
    private String belongSystem;

    /**
     * 所属业务
     */
    private String belongBusiness;
}
