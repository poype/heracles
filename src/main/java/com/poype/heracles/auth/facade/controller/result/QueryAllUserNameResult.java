package com.poype.heracles.auth.facade.controller.result;

import com.poype.heracles.common.dto.BaseResult;

import java.util.List;

public class QueryAllUserNameResult extends BaseResult {

    private List<String> userNameList;

    @Override
    public String toString() {
        return "QueryAllUserNameResult{" +
                "userNameList=" + userNameList +
                ", " + super.toString() +
                '}';
    }

    public List<String> getUserNameList() {
        return userNameList;
    }

    public void setUserNameList(List<String> userNameList) {
        this.userNameList = userNameList;
    }
}
