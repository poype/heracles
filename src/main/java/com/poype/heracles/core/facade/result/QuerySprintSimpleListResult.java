package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.core.domain.model.dto.SimpleSprintDto;

import java.util.List;

public class QuerySprintSimpleListResult extends BaseResult {

    private List<SimpleSprintDto> sprintList;

    private int total;

    @Override
    public String toString() {
        return "QuerySprintSimpleListResult{" +
                "sprintList=" + sprintList +
                ", total=" + total +
                ", " + super.toString() +
                '}';
    }

    public List<SimpleSprintDto> getSprintList() {
        return sprintList;
    }

    public void setSprintList(List<SimpleSprintDto> sprintList) {
        this.sprintList = sprintList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
