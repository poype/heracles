package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.core.facade.request.AddApplicationRequest;

public class QueryApplicationDetailResult extends BaseResult {

    /**
     * 应用信息，与添加应用的时候一样
     */
    private AddApplicationRequest app;

    @Override
    public String toString() {
        return "QueryApplicationDetailResult{" +
                "app=" + app +
                ", " + super.toString() +
                '}';
    }

    public AddApplicationRequest getApp() {
        return app;
    }

    public void setApp(AddApplicationRequest app) {
        this.app = app;
    }
}
