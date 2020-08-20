package com.poype.heracles.core.facade.request;

import com.poype.heracles.core.domain.model.dto.AppOfSprintDto;

import java.util.List;

public class CreateNewSprintRequest {

    /**
     * 版本名称
     */
    private String name;

    /**
     * 版本描述
     */
    private String description;

    /**
     * 发版日期
     */
    private String releaseDate;

    /**
     * 相关应用
     */
    private List<AppOfSprintDto> appList;

    @Override
    public String toString() {
        return "CreateNewSprintRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", appList=" + appList +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<AppOfSprintDto> getAppList() {
        return appList;
    }

    public void setAppList(List<AppOfSprintDto> appList) {
        this.appList = appList;
    }
}
