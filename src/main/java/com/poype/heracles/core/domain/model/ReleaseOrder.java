package com.poype.heracles.core.domain.model;

import com.poype.heracles.common.util.IdUtil;
import com.poype.heracles.core.domain.model.enums.ReleaseItemStatus;
import com.poype.heracles.core.domain.model.enums.ReleaseOrderStatus;
import com.poype.heracles.core.domain.model.sprint.AppOfSprint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 发布主单
 */
public class ReleaseOrder {

    /**
     * 发布单Id
     */
    private String orderId;

    /**
     * 发布名称
     */
    private String releaseName;

    /**
     * 描述
     */
    private String description;

    /**
     * 要发布的一组应用列表
     */
    private List<ReleaseItem> releaseItems;

    /**
     * 发布环境
     */
    private String envName;

    /**
     * 整体发布状态
     */
    private ReleaseOrderStatus status;

    /**
     * 操作者
     */
    private String operator;

    /**
     * 发布时间
     */
    private Date releaseDate;

    /**
     * 关联的版本Id
     */
    private String sprintId;

    public void updateItemStatus(String itemId, ReleaseItemStatus status) {
        for (ReleaseItem item : this.releaseItems) {
            if (item.getItemId().equals(itemId)) {
                item.setStatus(status);
                break;
            }
        }
        boolean finishFlag = true;
        for (ReleaseItem item : this.releaseItems) {
            if (item.getStatus() == ReleaseItemStatus.PROCESSING
                    || item.getStatus() == ReleaseItemStatus.INIT) {
                finishFlag = false;
                break;
            }
        }
        if (finishFlag) {
            this.status = ReleaseOrderStatus.FINISH;
        }
    }

    /**
     * 添加一个版本中的应用到发布单中
     * @param appOfSprint 版本中的应用
     */
    public void addAppToRelease(AppOfSprint appOfSprint, String codeRepository) {
        ReleaseItem item = new ReleaseItem(this.orderId, appOfSprint.getApp(), codeRepository,
                appOfSprint.getCodeBranch());
        this.releaseItems.add(item);
    }

    public ReleaseOrder(String releaseName, String description, String envName,
                        String operator, String sprintId) {
        this.orderId = IdUtil.generateBizId();
        this.status = ReleaseOrderStatus.INIT;
        this.releaseDate = new Date();
        this.releaseItems = new ArrayList<>();
        this.releaseName = releaseName;
        this.description = description;
        this.envName = envName;
        this.operator = operator;
        this.sprintId = sprintId;
    }

    public ReleaseOrder(String orderId, String releaseName, String description, List<ReleaseItem> releaseItems,
                        String envName, ReleaseOrderStatus status, String operator, Date releaseDate) {
        this.orderId = orderId;
        this.releaseName = releaseName;
        this.description = description;
        this.releaseItems = releaseItems;
        this.envName = envName;
        this.status = status;
        this.operator = operator;
        this.releaseDate = releaseDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<ReleaseItem> getReleaseItems() {
        return releaseItems;
    }

    public void setReleaseItems(List<ReleaseItem> releaseItems) {
        this.releaseItems = releaseItems;
    }

    public String getEnvName() {
        return envName;
    }

    public void setEnvName(String envName) {
        this.envName = envName;
    }

    public ReleaseOrderStatus getStatus() {
        return status;
    }

    public void setStatus(ReleaseOrderStatus status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getReleaseName() {
        return releaseName;
    }

    public void setReleaseName(String releaseName) {
        this.releaseName = releaseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }
}
