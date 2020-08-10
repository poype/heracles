package com.poype.heracles.core.facade.request;

public class QueryApplicationRequest {

    /**
     * 应用Id
     */
    private String appId;

    /**
     * 页号
     */
    private int pageNum;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
