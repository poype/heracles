package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.core.domain.model.Environment;

import java.util.List;

public class QueryAllEnvironmentResult extends BaseResult {

    private List<Environment> envList;

    @Override
    public String toString() {
        return "QueryAllEnvironmentResult{" +
                "envList=" + envList +
                '}';
    }

    public List<Environment> getEnvList() {
        return envList;
    }

    public void setEnvList(List<Environment> envList) {
        this.envList = envList;
    }
}
