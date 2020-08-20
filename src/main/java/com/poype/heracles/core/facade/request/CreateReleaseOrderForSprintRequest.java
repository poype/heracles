package com.poype.heracles.core.facade.request;


/**
 * 发布单创建对象
 */
public class CreateReleaseOrderForSprintRequest {
    /**
     * 版本Id
     */
    private String sprintId;

    /**
     * 应用列表
     */
    private String app;

    @Override
    public String toString() {
        return "CreateReleaseOrderForSprintRequest{" +
                "sprintId='" + sprintId + '\'' +
                ", app='" + app + '\'' +
                '}';
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }
}
