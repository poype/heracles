package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.core.domain.model.dto.SimpleApplicationDto;

import java.util.List;

public class QueryApplicationSimpleListResult extends BaseResult {

    private List<SimpleApplicationDto> appList;

    private int total;

    @Override
    public String toString() {
        return "QueryApplicationSimpleListResult{" +
                "appList=" + appList +
                ", total=" + total +
                ", " + super.toString() +
                '}';
    }

    public List<SimpleApplicationDto> getAppList() {
        return appList;
    }

    public void setAppList(List<SimpleApplicationDto> appList) {
        this.appList = appList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
