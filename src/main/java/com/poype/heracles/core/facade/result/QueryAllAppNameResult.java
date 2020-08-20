package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;

import java.util.List;

public class QueryAllAppNameResult extends BaseResult {

    private List<String> appNameList;

    @Override
    public String toString() {
        return "QueryAllAppNameResult{" +
                "appNameList=" + appNameList +
                ", " + super.toString() +
                '}';
    }

    public List<String> getAppNameList() {
        return appNameList;
    }

    public void setAppNameList(List<String> appNameList) {
        this.appNameList = appNameList;
    }
}
