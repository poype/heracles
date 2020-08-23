package com.poype.heracles.core.facade.request;

public class TransferSprintStatusRequest {

    private String sprintId;

    private String status;

    public TransferSprintStatusRequest(String sprintId, String status) {
        this.sprintId = sprintId;
        this.status = status;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
