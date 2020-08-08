package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;

public class QueryReleaseOrderStatusResult extends BaseResult {

    private ReleaseOrderView releaseOrderView;

    @Override
    public String toString() {
        return "QueryReleaseOrderStatusResult{" +
                "releaseOrderView=" + releaseOrderView +
                ", " + super.toString() +
                '}';
    }

    public ReleaseOrderView getReleaseOrderView() {
        return releaseOrderView;
    }

    public void setReleaseOrderView(ReleaseOrderView releaseOrderView) {
        this.releaseOrderView = releaseOrderView;
    }
}
