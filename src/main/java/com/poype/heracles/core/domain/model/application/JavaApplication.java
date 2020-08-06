package com.poype.heracles.core.domain.model.application;

import com.poype.heracles.core.domain.model.HostConfig;

import java.util.List;

/**
 * Java应用领域模型
 */
public class JavaApplication extends Application {

    /**
     * 域名配置信息
     */
    private List<HostConfig> hosts;

    public JavaApplication(String applicationName, String applicationType, String description,
                           String codeRepository, String devOwner, String qaOwner, String belongSystem,
                           String belongBusiness, List<HostConfig> hosts) {
        super(applicationName, applicationType, description, codeRepository, devOwner, qaOwner,
                belongSystem, belongBusiness);
        this.hosts = hosts;
    }
}
