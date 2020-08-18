package com.poype.heracles.core.repository.dao.model;

import java.util.Date;

public class SprintDO {
    /**
     * 版本Id
     */
    private String sprintId;

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
    private int status;

    /**
     * 该版本使用的SIT环境
     */
    private String sitEnvName;

    private Date createTime;

    private Date updateTime;

    public SprintDO(String sprintId, String sprintName, String description, String releaseDate,
                    int status, String sitEnvName) {
        this.sprintId = sprintId;
        this.sprintName = sprintName;
        this.description = description;
        this.releaseDate = releaseDate;
        this.status = status;
        this.sitEnvName = sitEnvName;
    }

    public SprintDO() {
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getSitEnvName() {
        return sitEnvName;
    }

    public void setSitEnvName(String sitEnvName) {
        this.sitEnvName = sitEnvName;
    }
}
