package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.core.domain.model.dto.SimpleReleaseOrderDto;

import java.util.List;

public class QuerySimpleReleaseOrderOfSprintResult extends BaseResult {

    private List<SimpleReleaseOrderDto> releaseList;

    @Override
    public String toString() {
        return "QuerySimpleReleaseOrderOfSprintResult{" +
                "releaseList=" + releaseList +
                ", " + super.toString() +
                '}';
    }

    public List<SimpleReleaseOrderDto> getReleaseList() {
        return releaseList;
    }

    public void setReleaseList(List<SimpleReleaseOrderDto> releaseList) {
        this.releaseList = releaseList;
    }
}
