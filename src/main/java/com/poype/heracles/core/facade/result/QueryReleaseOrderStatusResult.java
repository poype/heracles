package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;

public class QueryReleaseOrderStatusResult extends BaseResult {

    private ReleaseOrderView releaseOrder;

    @Override
    public String toString() {
        return "QueryReleaseOrderStatusResult{" +
                "releaseOrder=" + releaseOrder +
                ", " + super.toString() +
                '}';
    }

    public ReleaseOrderView getReleaseOrder() {
        return releaseOrder;
    }

    public void setReleaseOrder(ReleaseOrderView releaseOrder) {
        this.releaseOrder = releaseOrder;
    }
}
