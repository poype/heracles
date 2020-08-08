package com.poype.heracles.core.domain.model;

/**
 * 发布单中的一个应用
 */
public class AppOfRelease {

    /**
     * 应用名字
     */
    private String appName;

    /**
     * 代码分支
     */
    private String codeBranch;

    @Override
    public String toString() {
        return "AppOfRelease{" +
                "appName='" + appName + '\'' +
                ", codeBranch='" + codeBranch + '\'' +
                '}';
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCodeBranch() {
        return codeBranch;
    }

    public void setCodeBranch(String codeBranch) {
        this.codeBranch = codeBranch;
    }
}
