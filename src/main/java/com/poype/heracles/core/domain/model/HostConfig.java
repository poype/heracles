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
    private String prodHostName;

    /**
     * 测试环境域名
     */
    private String testHostName;

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getProdHostName() {
        return prodHostName;
    }

    public void setProdHostName(String prodHostName) {
        this.prodHostName = prodHostName;
    }

    public String getTestHostName() {
        return testHostName;
    }

    public void setTestHostName(String testHostName) {
        this.testHostName = testHostName;
    }
}
