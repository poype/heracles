package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;

import java.util.List;

public class QueryAllHostConfigNameResult extends BaseResult {

    private List<String> hostNameList;

    @Override
    public String toString() {
        return "QueryAllHostConfigNameResult{" +
                "hostNameList=" + hostNameList +
                ", " + super.toString() +
                '}';
    }

    public List<String> getHostNameList() {
        return hostNameList;
    }

    public void setHostNameList(List<String> hostNameList) {
        this.hostNameList = hostNameList;
    }
}
