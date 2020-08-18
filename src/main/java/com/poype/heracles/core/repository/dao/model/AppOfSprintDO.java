package com.poype.heracles.core.repository.dao.model;

import java.util.Date;

public class AppOfSprintDO {
    /**
     * 应用与迭代关联记录Id
     */
    private String relationId;

    /**
     * 版本Id
     */
    private String sprintId;

    /**
     * 应用名字
     */
    private String app;

    /**
     * 应用类型
     */
    private int appType;

    /**
     * 代码仓库地址
     */
    private String codeRepository;

    /**
     * 本次迭代中的代码分支
     */
    private String codeBranch;

    /**
     * 研发人员集合
     */
    private String devS;

    /**
     * 测试人员集合
     */
    private String qaS;

    /**
     * 应用状态
     */
    private int status;

    private Date createTime;

    private Date updateTime;

    public AppOfSprintDO(String relationId, String sprintId, String app, int appType, String codeRepository,
                         String codeBranch, String devS, String qaS, int status) {
        this.relationId = relationId;
        this.sprintId = sprintId;
        this.app = app;
        this.appType = appType;
        this.codeRepository = codeRepository;
        this.codeBranch = codeBranch;
        this.devS = devS;
        this.qaS = qaS;
        this.status = status;
    }

    public AppOfSprintDO() {
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
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

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

    public String getCodeRepository() {
        return codeRepository;
    }

    public void setCodeRepository(String codeRepository) {
        this.codeRepository = codeRepository;
    }

    public String getCodeBranch() {
        return codeBranch;
    }

    public void setCodeBranch(String codeBranch) {
        this.codeBranch = codeBranch;
    }

    public String getDevS() {
        return devS;
    }

    public void setDevS(String devS) {
        this.devS = devS;
    }

    public String getQaS() {
        return qaS;
    }

    public void setQaS(String qaS) {
        this.qaS = qaS;
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
}
