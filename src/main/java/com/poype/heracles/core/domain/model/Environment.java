package com.poype.heracles.core.domain.model;

import com.poype.heracles.core.domain.model.enums.EnvironmentStatus;
import com.poype.heracles.core.domain.model.enums.EnvironmentType;

/**
 * 环境对象
 */
public class Environment {
    /**
     * 环境Id
     */
    private int envId;

    /**
     * 环境名称
     */
    private String envName;

    /**
     * 环境类型
     */
    private EnvironmentType envType;

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

    public Environment(String envName, EnvironmentType envType, int defaultCpuOfApp, int defaultMemoryOfApp) {
        this.envName = envName;
        this.envType = envType;
        this.defaultCpuOfApp = defaultCpuOfApp;
        this.defaultMemoryOfApp = defaultMemoryOfApp;
        this.status = EnvironmentStatus.FREE;
    }

    public Environment(int envId, String envName, EnvironmentType envType, int defaultCpuOfApp,
                       int defaultMemoryOfApp, int totalCpu, int totalMemory, EnvironmentStatus status) {
        this.envId = envId;
        this.envName = envName;
        this.envType = envType;
        this.defaultCpuOfApp = defaultCpuOfApp;
        this.defaultMemoryOfApp = defaultMemoryOfApp;
        this.totalCpu = totalCpu;
        this.totalMemory = totalMemory;
        this.status = status;
    }

    public int getEnvId() {
        return envId;
    }

    public void setEnvId(int envId) {
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

    public EnvironmentType getEnvType() {
        return envType;
    }

    public void setEnvType(EnvironmentType envType) {
        this.envType = envType;
    }
}
