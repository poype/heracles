package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;

public class TransferAppStatusResult extends BaseResult {

    private String sprintStatus;

    @Override
    public String toString() {
        return "TransferAppStatusResult{" +
                "sprintStatus='" + sprintStatus + '\'' +
                ", " + super.toString() +
                '}';
    }

    public String getSprintStatus() {
        return sprintStatus;
    }

    public void setSprintStatus(String sprintStatus) {
        this.sprintStatus = sprintStatus;
    }
}
