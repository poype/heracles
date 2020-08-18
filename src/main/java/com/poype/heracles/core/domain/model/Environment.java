package com.poype.heracles.core.domain.model;

import com.poype.heracles.common.util.IdUtil;
import com.poype.heracles.core.domain.model.enums.EnvironmentStatus;

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
     * 该环境下应用的默认CPU资源
     */
    private int defaultCpuOfApp;

    /**
     * 该环境下应用的默认内存资源
     */
    private int defaultMemoryOfApp;

    /**
     * 该环境下占用的总cpu资源
     */
    private int totalCpu;

    /**
     * 该环境下占用的总memory资源
     */
    private int totalMemory;

    /**
     * 环境状态
     */
    private EnvironmentStatus status;

    public Environment(String envName, int defaultCpuOfApp, int defaultMemoryOfApp) {
        this.envId = IdUtil.generateBizId();
        this.status = EnvironmentStatus.FREE;
        this.envName = envName;
        this.defaultCpuOfApp = defaultCpuOfApp;
        this.defaultMemoryOfApp = defaultMemoryOfApp;
    }

    public String getEnvId() {
        return envId;
    }

    public void setEnvId(String envId) {
        this.envId = envId;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public int getDefaultCpuOfApp() {
        return defaultCpuOfApp;
    }

    public void setDefaultCpuOfApp(int defaultCpuOfApp) {
        this.defaultCpuOfApp = defaultCpuOfApp;
    }

    public int getDefaultMemoryOfApp() {
        return defaultMemoryOfApp;
    }

    public void setDefaultMemoryOfApp(int defaultMemoryOfApp) {
        this.defaultMemoryOfApp = defaultMemoryOfApp;
    }

    public int getTotalCpu() {
        return totalCpu;
    }

    public void setTotalCpu(int totalCpu) {
        this.totalCpu = totalCpu;
    }

    public int getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(int totalMemory) {
        this.totalMemory = totalMemory;
    }

    public EnvironmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnvironmentStatus status) {
        this.status = status;
    }
}
