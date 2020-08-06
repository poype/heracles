package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;

public class CreateNewSprintResult extends BaseResult {

    /**
     * 版本Id
     */
    private String sprintId;

    @Override
    public String toString() {
        return "CreateNewSprintResult{" +
                "sprintId='" + sprintId + '\'' +
                ", " + super.toString() +
                '}';
    }

    public String getSprintId() {
        return sprintId;
    }

    public void setSprintId(String sprintId) {
        this.sprintId = sprintId;
    }
}
