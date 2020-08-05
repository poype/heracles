package com.poype.common.exception;

import com.poype.common.dto.error.BizScene;
import com.poype.common.enums.BusinessErrorCode;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -4647774772460071613L;

    private BusinessErrorCode businessError;

    private BizScene bizScene;

    /** 无参构造方法 */
    public BusinessException() {
        super();
    }

    /** 基于描述的构造方法 */
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(BusinessErrorCode businessError) {
        super();
        this.businessError = businessError;
    }

    public BusinessException(String message, BusinessErrorCode businessError) {
        super(message);
        this.businessError = businessError;
    }

    public BusinessException(BusinessErrorCode businessError, Exception e) {
        super(e);
        this.businessError = businessError;
    }

    public BusinessErrorCode getBusinessError() {
        return businessError;
    }

    public void setBusinessError(BusinessErrorCode businessError) {
        this.businessError = businessError;
    }

    public BizScene getBizScene() {
        return bizScene;
    }

    public void setBizScene(BizScene bizScene) {
        this.bizScene = bizScene;
    }
}

