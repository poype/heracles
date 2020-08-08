package com.poype.heracles.core.domain.model;

import com.poype.heracles.core.domain.model.enums.EnvironmentStatus;
import com.poype.heracles.core.domain.model.enums.EnvironmentType;

import java.util.List;

/**
 * 环境对象
 */
public class Environment {

    /**
     * 环境Id
     */
    private String envId;

    /**
     * 环境名称
     */
    private String envName;

    /**
     * 该环境下的所有应用
     */
    private List<ApplicationOfEnvironment> applications;

    /**
     * 该环境下应用的默认CPU资源
     */
    private double defaultCpuOfApp;

    /**
     * 该环境下应用的默认内存资源
     */
    private int defaultMemoryOfApp;

    /**
     * 该环境下占用的总cpu资源
     */
    private double totalCpu;

    /**
     * 该环境下占用的总memory资源
     */
    private int totalMemory;

    /**
     * 环境状态
     */
    private EnvironmentStatus status;

}
