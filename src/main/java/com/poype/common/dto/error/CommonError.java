package com.poype.common.dto.error;

import java.io.Serializable;

public class CommonError implements Serializable {
    private static final long serialVersionUID = 8408717826691281869L;

    /* 错误编码 */
    private ErrorCode errorCode;

    /* 错误描述 */
    private String errorMsg;

    public CommonError(ErrorCode errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return errorCode + "@" + errorMsg;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
