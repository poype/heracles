package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.core.domain.model.dto.SimpleApplicationDto;

import java.util.List;

public class QueryApplicationSimpleListResult extends BaseResult {

    private List<SimpleApplicationDto> appList;

    @Override
    public String toString() {
        return "QueryApplicationSimpleListResult{" +
                "appList=" + appList +
                ", " + super.toString() +
                '}';
    }

    public List<SimpleApplicationDto> getAppList() {
        return appList;
    }

    public void setAppList(List<SimpleApplicationDto> appList) {
        this.appList = appList;
    }
}
