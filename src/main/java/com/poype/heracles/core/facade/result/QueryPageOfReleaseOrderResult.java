package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.core.domain.model.dto.SimpleReleaseOrderDto;

import java.util.List;

public class QueryPageOfReleaseOrderResult extends BaseResult {

    private List<SimpleReleaseOrderDto> releaseOrderList;

    private int total;

    @Override
    public String toString() {
        return "QueryPageOfReleaseOrderResult{" +
                "releaseOrderList=" + releaseOrderList +
                ", total=" + total +
                '}';
    }

    public List<SimpleReleaseOrderDto> getReleaseOrderList() {
        return releaseOrderList;
    }

    public void setReleaseOrderList(List<SimpleReleaseOrderDto> releaseOrderList) {
        this.releaseOrderList = releaseOrderList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
