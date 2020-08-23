package com.poype.heracles.core.facade.request;

public class TransferAppStatusRequest {

    private String sprintId;

    private String app;

    private String status;

    @Override
    public String toString() {
        return "TransferAppStatusRequest{" +
                "sprintId='" + sprintId + '\'' +
                ", app='" + app + '\'' +
                ", status='" + status + '\'' +
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
