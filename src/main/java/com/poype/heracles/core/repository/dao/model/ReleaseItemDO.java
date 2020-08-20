package com.poype.heracles.core.repository.dao.model;

import java.util.Date;

public class ReleaseItemDO {
    /**
     * 发布项Id
     */
    private String itemId;

    /**
     * 发布单Id
     */
    private String orderId;

    /**
     * 发布项状态
     */
    private int status;

    /**
     * 应用名字
     */
    private String appName;

    /**
     * 代码仓库url
     */
    private String codeRepos;

    /**
     * 代码分支
     */
    private String codeBranch;

    private Date createTime;

    private Date updateTime;

    public ReleaseItemDO(String itemId, String orderId, int status, String appName, String codeRepos,
                         String codeBranch) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.status = status;
        this.appName = appName;
        this.codeRepos = codeRepos;
        this.codeBranch = codeBranch;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
