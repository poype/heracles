package com.poype.heracles.core.domain.model;

import com.poype.heracles.core.domain.model.enums.EnvironmentStatus;
import com.poype.heracles.core.domain.model.enums.EnvironmentType;

public class Environment {

    /**
     * 环境Id
     */
    private String environmentId;

    /**
     * 环境类型
     */
    private EnvironmentType environmentType;

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
