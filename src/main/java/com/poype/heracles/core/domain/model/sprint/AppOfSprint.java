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
    private List<String> devSet;

    /**
     * 测试人员集合
     */
    private List<String> qaSet;

    /**
     * 应用状态
     */
    private AppOfSprintStatus status;

    public AppOfSprint(String app, List<String> devSet, List<String> qaSet, String codeBranch,
                       String codeRepository, ApplicationType appType) {
        this.relationId = IdUtil.generateBizId();
        this.status = AppOfSprintStatus.INIT;
        this.app = app;
        this.devSet = devSet;
        this.qaSet = qaSet;
        this.codeBranch = codeBranch;
        this.codeRepository = codeRepository;
        this.appType = appType;
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

    public List<String> getDevSet() {
        return devSet;
    }

    public void setDevSet(List<String> devSet) {
        this.devSet = devSet;
    }

    public List<String> getQaSet() {
        return qaSet;
    }

    public void setQaSet(List<String> qaSet) {
        this.qaSet = qaSet;
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
