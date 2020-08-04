package com.poype.heracles.core.domain.model;

import com.poype.heracles.core.domain.model.enums.ApplicationOfSprintStatus;

import java.util.List;
import java.util.Set;

/**
 * 关联在一个迭代中的应用
 */
public class ApplicationOfSprint {

    /**
     * 应用与迭代关联记录Id
     */
    private String relationId;

    /**
     * 应用
     */
    private Application application;

    /**
     * 本次迭代中的代码分支
     */
    private String codeBranch;

    /**
     * 研发人员集合
     */
    private Set<String> devSet;

    /**
     * 测试人员集合
     */
    private Set<String> qaSet;

    /**
     * 本次迭代对应用host配置变化
     */
    private List<HostConfig> hostConfigs;

    /**
     * 应用状态
     */
    private ApplicationOfSprintStatus status;
}
