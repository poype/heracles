package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;

/**
 * 查询版本详情result
 */
public class QuerySprintDetailResult extends BaseResult {

    private SprintView sprintInfo;

    @Override
    public String toString() {
        return "QuerySprintDetailResult{" +
                "sprintInfo=" + sprintInfo +
                ", " + super.toString() +
                '}';
    }

    public SprintView getSprintInfo() {
        return sprintInfo;
    }

    public void setSprintInfo(SprintView sprintInfo) {
        this.sprintInfo = sprintInfo;
    }
}
