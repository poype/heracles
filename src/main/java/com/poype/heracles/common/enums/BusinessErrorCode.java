package com.poype.heracles.common.enums;

import org.apache.commons.lang.StringUtils;

import static com.poype.heracles.common.dto.error.ErrorLevel.ERROR;
import static com.poype.heracles.common.dto.error.ErrorLevel.INFO;

public enum BusinessErrorCode {

    /*********************通用错误码，与业务无关*********************/
    /** 成功 */
    SUCCESS("000", INFO, "success"),

    /** 参数不合法 */
    PARAM_ILLEGAL("001", ERROR, "param illegal"),

    /** system error */
    SYSTEM_ERROR("999", ERROR, "system error"),

    GIT_ADDR_ILLEGAL("003", INFO, "Git仓库地址不合法"),

    RELEASE_ENV_ILLEGAL("004", INFO, "发布环境不合法"),

    GIT_PUSH_ERROR("005", INFO, "GIT PUSH ERROR"),

    GIT_CLONE_ERROR("006", INFO, "GIT CLONE ERROR"),

    GIT_PULL_ERROR("007", INFO, "GIT PULL ERROR"),

    GIT_CREATE_BRANCH_ERROR("008", INFO, "GIT CREATE BRANCH ERROR"),

    DUPLICATE_APP_NAME("009", INFO, "DUPLICATE APP NAME"),

    APP_NOT_FOUND("010", INFO, "APP NOT FOUND"),

    ILLEGAL_SPRINT_STATUS("011", INFO, "illegal sprint status"),

    ILLEGAL_ENVIRONMENT("012", INFO, "illegal environment"),

    NO_FREE_SIT_ENV("013", INFO, "can't find any free sit environment"),

    ENV_NOT_EXIST("014", INFO, "env not exist"),

    PICK_ENV_CONFLICT("015", INFO, "pick environment conflict"),
    ;

    private final String specificCode;

    private final String description;

    private final String errorLevel;

    /**
     *
     * @param specificCode 错误编码
     * @param errorLevel 错误级别
     * @param description 错误描述
     */
    BusinessErrorCode(String specificCode, String errorLevel, String description) {
        this.specificCode = specificCode;
        this.errorLevel = errorLevel;
        this.description = description;
    }

    /**
     * 根据错误编码获取枚举对象
     * @param code 错误编码
     * @return 错误枚举对象
     */
    public static BusinessErrorCode getBySpecificCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (BusinessErrorCode errorCode : values()) {
            if (StringUtils.equalsIgnoreCase(errorCode.getSpecificCode(), code)) {
                return errorCode;
            }
        }
        return null;
    }

    public String getResultCode() {
        return null;
    }

    public String getResultMsg() {
        return null;
    }


    public String getSpecificCode() {
        return specificCode;
    }

    public String getDescription() {
        return description;
    }

    public String getErrorLevel() {
        return errorLevel;
    }

}
