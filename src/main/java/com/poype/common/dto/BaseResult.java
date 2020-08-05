package com.poype.common.dto;

import com.poype.common.dto.error.CommonError;

import java.io.Serializable;

public class BaseResult implements Serializable {

    private static final long serialVersionUID = 3008364466981920509L;

    /** 业务处理是否成功 */
    private boolean success;

    private CommonError error;

    @Override
    public String toString() {
        return "success=" + success +
                ", error=" + error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public CommonError getError() {
        return error;
    }

    public void setError(CommonError error) {
        this.error = error;
    }
}
