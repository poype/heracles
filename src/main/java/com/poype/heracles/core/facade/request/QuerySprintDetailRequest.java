package com.poype.heracles.core.facade.request;

public class QuerySprintDetailRequest {

    /**
     * 版本Id
     */
    private String sprintId;

    @Override
    public String toString() {
        return "QuerySprintDetailRequest{" +
                "sprintId='" + sprintId + '\'' +
                '}';
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }
}
