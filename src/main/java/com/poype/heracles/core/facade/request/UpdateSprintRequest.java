package com.poype.heracles.core.facade.request;

import com.poype.heracles.core.domain.model.dto.AppOfSprintDto;

import java.util.List;

public class UpdateSprintRequest {

    private String sprintId;

    private List<AppOfSprintDto> appList;

    @Override
    public String toString() {
        return "UpdateSprintRequest{" +
                "sprintId='" + sprintId + '\'' +
                ", appList=" + appList +
                '}';
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }

    public List<AppOfSprintDto> getAppList() {
        return appList;
    }

    public void setAppList(List<AppOfSprintDto> appList) {
        this.appList = appList;
    }
}
