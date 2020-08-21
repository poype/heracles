package com.poype.heracles.core.domain.model.sprint;

import com.poype.heracles.common.util.IdUtil;
import com.poype.heracles.core.domain.model.enums.AppOfSprintStatus;
import com.poype.heracles.core.domain.model.enums.ApplicationType;

import java.util.List;

/**
 * 关联在一个迭代中的应用
 */
public class AppOfSprint {

    /**
     * 应用与迭代关联记录Id
     */
    private String relationId;

    /**
     * 应用名字
     */
    private String app;

    /**
     * 应用类型
     */
    private ApplicationType appType;

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
    private List<String> devList;

    /**
     * 测试人员集合
     */
    private List<String> qaList;

    /**
     * 应用状态
     */
    private AppOfSprintStatus status;

    public AppOfSprint(String app, List<String> devList, List<String> qaList, String codeBranch,
                       String codeRepository, ApplicationType appType) {
        this.relationId = IdUtil.generateBizId();
        this.status = AppOfSprintStatus.INIT;
        this.app = app;
        this.devList = devList;
        this.qaList = qaList;
        this.codeBranch = codeBranch;
        this.codeRepository = codeRepository;
        this.appType = appType;
    }

    public AppOfSprint(String relationId, String app, ApplicationType appType, String codeRepository,
                       String codeBranch, List<String> devList, List<String> qaList, AppOfSprintStatus status) {
        this.relationId = relationId;
        this.app = app;
        this.appType = appType;
        this.codeRepository = codeRepository;
        this.codeBranch = codeBranch;
        this.devList = devList;
        this.qaList = qaList;
        this.status = status;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getCodeBranch() {
        return codeBranch;
    }

    public void setCodeBranch(String codeBranch) {
        this.codeBranch = codeBranch;
    }

    public List<String> getDevList() {
        return devList;
    }

    public void setDevList(List<String> devList) {
        this.devList = devList;
    }

    public List<String> getQaList() {
        return qaList;
    }

    public void setQaList(List<String> qaList) {
        this.qaList = qaList;
    }

    public AppOfSprintStatus getStatus() {
        return status;
    }

    public void setStatus(AppOfSprintStatus status) {
        this.status = status;
    }

    public ApplicationType getAppType() {
        return appType;
    }

    public void setAppType(ApplicationType appType) {
        this.appType = appType;
    }

    public String getCodeRepository() {
        return codeRepository;
    }

    public void setCodeRepository(String codeRepository) {
        this.codeRepository = codeRepository;
    }
}
