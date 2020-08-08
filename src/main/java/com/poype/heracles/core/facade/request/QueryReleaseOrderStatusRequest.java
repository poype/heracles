package com.poype.heracles.core.facade.request;

/**
 * 查询发布单状态request
 */
public class QueryReleaseOrderStatusRequest {

    /**
     * 发布单ID
     */
    private String releaseOrderId;

    public String getReleaseOrderId() {
        return releaseOrderId;
    }

    public void setReleaseOrderId(String releaseOrderId) {
        this.releaseOrderId = releaseOrderId;
    }
}
