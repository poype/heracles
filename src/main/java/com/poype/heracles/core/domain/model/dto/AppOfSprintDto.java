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
    private List<String> devList;

    /**
     * 测试参与人
     */
    private List<String> qaList;

    public AppOfSprintDto(String appName, List<String> devList, List<String> qaList) {
        this.appName = appName;
        this.devList = devList;
        this.qaList = qaList;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public List<String> getDevList() {
        return devList;
    }

    public void setDevList(List<String> devList) {
        this.devList = devList;
    }

    public List<String> getQaList() {
        return qaList;
    }

    public void setQaList(List<String> qaList) {
        this.qaList = qaList;
    }
}
