package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.core.domain.model.application.Application;

import java.util.List;

public class QueryApplicationResult extends BaseResult {

    /**
     * 应用列表
     */
    private List<Application> appList;

    @Override
    public String toString() {
        return "QueryApplicationResult{" +
                "appList=" + appList +
                ", " + super.toString() +
                '}';
    }

    public List<Application> getAppList() {
        return appList;
    }

    public void setAppList(List<Application> appList) {
        this.appList = appList;
    }
}
