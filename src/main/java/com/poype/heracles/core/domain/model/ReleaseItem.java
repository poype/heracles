package com.poype.heracles.core.domain.model;

import com.poype.heracles.common.util.IdUtil;
import com.poype.heracles.core.domain.model.enums.ReleaseItemStatus;

/**
 * 发布项
 */
public class ReleaseItem {

    /**
     * 发布单Id
     */
    private String orderId;

    /**
     * 发布项Id
     */
    private String itemId;

    /**
     * 发布项状态
     */
    private ReleaseItemStatus status;

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

    public ReleaseItem(String orderId, String appName, String codeRepos, String codeBranch) {
        this.itemId = IdUtil.generateBizId();
        this.status = ReleaseItemStatus.INIT;
        this.orderId = orderId;
        this.appName = appName;
        this.codeRepos = codeRepos;
        this.codeBranch = codeBranch;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public ReleaseItemStatus getStatus() {
        return status;
    }

    public void setStatus(ReleaseItemStatus status) {
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
}
