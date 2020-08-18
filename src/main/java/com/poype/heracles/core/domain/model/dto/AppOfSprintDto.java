package com.poype.heracles.core.domain.model.dto;

import java.util.List;

public class AppOfSprintDto {

    /**
     * 应用名字
     */
    private String appName;

    /**
     * 开发参与人
     */
    private List<String> devNames;

    /**
     * 测试参与人
     */
    private List<String> qaNames;

    public AppOfSprintDto(String appName, List<String> devNames, List<String> qaNames) {
        this.appName = appName;
        this.devNames = devNames;
        this.qaNames = qaNames;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<String> getDevNames() {
        return devNames;
    }

    public void setDevNames(List<String> devNames) {
        this.devNames = devNames;
    }

    public List<String> getQaNames() {
        return qaNames;
    }

    public void setQaNames(List<String> qaNames) {
        this.qaNames = qaNames;
    }
}
