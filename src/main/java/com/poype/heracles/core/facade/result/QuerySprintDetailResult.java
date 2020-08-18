package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;

import java.util.List;

/**
 * 查询版本详情result
 */
public class QuerySprintDetailResult extends BaseResult {

    /**
     * 版本Id
     */
    private String sprintId;

    /**
     * 版本名字
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
     * 版本状态
     */
    private String status;

    /**
     * 占用测试环境
     */
    private String testEnv;

    /**
     * 本次迭代中的所有应用
     */
    private List<AppOfSprintView> appList;

    @Override
    public String toString() {
        return "QuerySprintDetailResult{" +
                "sprintId='" + sprintId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", status='" + status + '\'' +
                ", testEnv='" + testEnv + '\'' +
                ", appList=" + appList +
                '}';
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTestEnv() {
        return testEnv;
    }

    public void setTestEnv(String testEnv) {
        this.testEnv = testEnv;
    }

    public List<AppOfSprintView> getAppList() {
        return appList;
    }

    public void setAppList(List<AppOfSprintView> appList) {
        this.appList = appList;
    }
}
