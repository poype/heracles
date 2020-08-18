package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;

public class CreateApplicationResult extends BaseResult {
    /**
     * 应用Id
     */
    private String applicationId;

    @Override
    public String toString() {
        return "CreateApplicationResult{" +
                "applicationId='" + applicationId + '\'' +
                ", " + super.toString() +
                '}';
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}
