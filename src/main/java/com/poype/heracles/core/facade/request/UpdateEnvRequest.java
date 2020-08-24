package com.poype.heracles.core.facade.request;

public class UpdateEnvRequest {

    private int envId;

    /**
     * 该环境下应用的默认CPU资源
     */
    private int defaultCpuOfApp;

    /**
     * 该环境下应用的默认内存资源
     */
    private int defaultMemoryOfApp;

    @Override
    public String toString() {
        return "UpdateEnvRequest{" +
                "envId=" + envId +
                ", defaultCpuOfApp=" + defaultCpuOfApp +
                ", defaultMemoryOfApp=" + defaultMemoryOfApp +
                '}';
    }

    public int getEnvId() {
        return envId;
    }

    public void setEnvId(int envId) {
        this.envId = envId;
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
}
