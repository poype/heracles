package com.poype.heracles.core.domain.model.sprint;

import com.poype.heracles.common.util.IdUtil;
import com.poype.heracles.core.domain.model.enums.AppOfSprintStatus;
import com.poype.heracles.core.domain.model.enums.SprintStatus;

import java.util.List;

/**
 * 版本
 */
public class Sprint {

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
    private SprintStatus status;

    /**
     * 与版本关联的应用
     */
    private List<AppOfSprint> applications;

    /**
     * 该版本使用的SIT环境
     */
    private String sitEnvName;

    public Sprint(String sprintName, String description, String releaseDate, List<AppOfSprint> applications,
                  String sitEnvName) {
        this.sprintId = IdUtil.generateBizId();
        this.status = SprintStatus.INIT;
        this.sprintName = sprintName;
        this.description = description;
        this.releaseDate = releaseDate;
        this.applications = applications;
        this.sitEnvName = sitEnvName;
    }

    public Sprint(String sprintId, String sprintName, String description, String releaseDate,
                  SprintStatus status, List<AppOfSprint> applications, String sitEnvName) {
        this.sprintId = sprintId;
        this.sprintName = sprintName;
        this.description = description;
        this.releaseDate = releaseDate;
        this.status = status;
        this.applications = applications;
        this.sitEnvName = sitEnvName;
    }

    public AppOfSprint findAppByName(String appName) {
        for (AppOfSprint appOfSprint : applications) {
            if (appOfSprint.getApp().equals(appName)) {
                return appOfSprint;
            }
        }
        return null;
    }

    /**
     * 单个应用的status转变后，检查整个sprint的status是否需要扭转
     * @param targetStatus 应用扭转到的status
     * @return sprint的status需要扭转返回true，否则返回false
     */
    public boolean checkSprintStatusAfterAppStatusTransfer(AppOfSprintStatus targetStatus) {
        for (AppOfSprint appOfSprint : applications) {
            // 存在一个应用的状态晚于变更应用的状态，则整个sprint的状态保持不变
            if (appOfSprint.getStatus().getCode() < targetStatus.getCode()) {
                return false;
            }
        }
        if (targetStatus == AppOfSprintStatus.SIT) {
            this.status = SprintStatus.SIT;
        }
        if (targetStatus == AppOfSprintStatus.UAT) {
            this.status = SprintStatus.UAT;
        }
        if (targetStatus == AppOfSprintStatus.FINISH) {
            this.status = SprintStatus.FINISH_TEST;
        }
        return true;

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

    public SprintStatus getStatus() {
        return status;
    }

    public void setStatus(SprintStatus status) {
        this.status = status;
    }

    public List<AppOfSprint> getApplications() {
        return applications;
    }

    public void setApplications(List<AppOfSprint> applications) {
        this.applications = applications;
    }

    public String getSitEnvName() {
        return sitEnvName;
    }

    public void setSitEnvName(String sitEnvName) {
        this.sitEnvName = sitEnvName;
    }
}
