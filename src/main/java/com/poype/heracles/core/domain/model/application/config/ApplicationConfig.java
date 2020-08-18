package com.poype.heracles.core.domain.model.application.config;

import com.poype.heracles.common.util.IdUtil;
import com.poype.heracles.core.domain.model.enums.ApplicationConfigTypeEnum;

public class ApplicationConfig {

    private String configId;

    private ApplicationConfigTypeEnum configType;

    private String configValue;

    public ApplicationConfig(ApplicationConfigTypeEnum configType, String configValue) {
        this.configId = IdUtil.generateBizId();
        this.configType = configType;
        this.configValue = configValue;
    }

    public ApplicationConfig(String configId, ApplicationConfigTypeEnum configType, String configValue) {
        this.configId = configId;
        this.configType = configType;
        this.configValue = configValue;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public ApplicationConfigTypeEnum getConfigType() {
        return configType;
    }

    public void setConfigType(ApplicationConfigTypeEnum configType) {
        this.configType = configType;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
