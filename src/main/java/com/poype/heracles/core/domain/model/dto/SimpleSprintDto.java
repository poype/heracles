package com.poype.heracles.core.domain.model.dto;

public class SimpleSprintDto {

    /**
     * 版本名称
     */
    private String sprintName;

    /**
     * 版本描述
     */
    private String description;

    /**
     * 发布日期
     */
    private String releaseDate;

    /**
     * 版本状态
     */
    private String status;

    public SimpleSprintDto() {
    }

    public SimpleSprintDto(String sprintName, String description, String releaseDate, String status) {
        this.sprintName = sprintName;
        this.description = description;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
