package com.poype.heracles.core.facade.request;

import com.poype.heracles.core.domain.model.dto.JavaApplicationDto;

public class UpdateJavaAppInfoRequest {

    private String appId;

    private JavaApplicationDto javaAppInfo;

    @Override
    public String toString() {
        return "UpdateJavaAppInfoRequest{" +
                "appId='" + appId + '\'' +
                ", javaAppInfo=" + javaAppInfo +
                '}';
    }

    public JavaApplicationDto getJavaAppInfo() {
        return javaAppInfo;
    }

    public void setJavaAppInfo(JavaApplicationDto javaAppInfo) {
        this.javaAppInfo = javaAppInfo;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
