package com.poype.heracles.core.domain.model;

/**
 * 域名配置
 */
public class HostConfig {

    /**
     * 配置名字
     */
    private String configName;

    /**
     * 生产环境域名
     */
    private String prodHost;

    /**
     * 测试环境域名
     */
    private String testHost;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getProdHost() {
        return prodHost;
    }

    public void setProdHost(String prodHost) {
        this.prodHost = prodHost;
    }

    public String getTestHost() {
        return testHost;
    }

    public void setTestHost(String testHost) {
        this.testHost = testHost;
    }
}
