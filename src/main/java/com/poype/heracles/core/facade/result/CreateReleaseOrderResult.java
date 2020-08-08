package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;

/**
 * 创建发布单结果对象
 */
public class CreateReleaseOrderResult extends BaseResult {

    /**
     * 发布单Id
     */
    private String releaseOrderId;

    @Override
    public String toString() {
        return "CreateReleaseOrderResult{" +
                "releaseOrderId='" + releaseOrderId + '\'' +
                ", " + super.toString() +
                '}';
    }

    public String getReleaseOrderId() {
        return releaseOrderId;
    }

    public void setReleaseOrderId(String releaseOrderId) {
        this.releaseOrderId = releaseOrderId;
    }
}
