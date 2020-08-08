package com.poype.heracles.core.facade.result;

public class ReleaseItemView {

    /**
     * 发布项Id
     */
    private String itemId;

    /**
     * 应用名字
     */
    private String appName;

    /**
     * 代码仓库
     */
    private String codeRepos;

    /**
     * 代码分支
     */
    private String codeBranch;

    /**
     * 发布项状态
     */
    private String status;

    public ReleaseItemView() {
    }

    public ReleaseItemView(String itemId, String appName, String codeRepos,
                           String codeBranch, String status) {
        this.itemId = itemId;
        this.appName = appName;
        this.codeRepos = codeRepos;
        this.codeBranch = codeBranch;
        this.status = status;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCodeRepos() {
        return codeRepos;
    }

    public void setCodeRepos(String codeRepos) {
        this.codeRepos = codeRepos;
    }

    public String getCodeBranch() {
        return codeBranch;
    }

    public void setCodeBranch(String codeBranch) {
        this.codeBranch = codeBranch;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
