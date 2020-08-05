package com.poype.common.enums;

import org.apache.commons.lang.StringUtils;

import static com.poype.common.dto.error.ErrorLevel.ERROR;
import static com.poype.common.dto.error.ErrorLevel.INFO;

public enum BusinessErrorCode {

    /*********************通用错误码，与业务无关*********************/
    /** 成功 */
    SUCCESS("000", INFO, "success"),

    /** 参数不合法 */
    PARAM_ILLEGAL("001", ERROR, "param illegal"),

    /** system error */
    SYSTEM_ERROR("002", ERROR, "system error"),

    /** 查询接口未找到记录 */
    TARGET_NOT_FOUND("003", INFO, "target not found"),

    /** 数据库存在脏数据 */
    DB_DIRTY_DATA("004", ERROR, "there are dirty data in the database"),

    /*********************用户信息相关错误码*********************/
    USER_HAS_REGISTER("005", ERROR, "user has register"),

    /*********************密码相关*********************/
    PASSWORD_EXIST("006", ERROR, "password exit")
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
