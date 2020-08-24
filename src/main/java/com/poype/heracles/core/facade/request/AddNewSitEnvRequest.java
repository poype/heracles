package com.poype.heracles.core.facade.request;

public class AddNewSitEnvRequest {

    /**
     * 环境名称
     */
    private String envName;

    /**
     * 环境类型
     */
    private String envType;

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
        return "AddNewSitEnvRequest{" +
                "envName='" + envName + '\'' +
                ", envType='" + envType + '\'' +
                ", defaultCpuOfApp=" + defaultCpuOfApp +
                ", defaultMemoryOfApp=" + defaultMemoryOfApp +
                '}';
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public String getEnvType() {
        return envType;
    }

    public void setEnvType(String envType) {
        this.envType = envType;
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
