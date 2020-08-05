package com.poype.heracles.core.domain.model;

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
    private List<ApplicationOfSprint> applications;

    /**
     * 该版本使用的SIT环境
     */
    private Environment sitEnv;
}
