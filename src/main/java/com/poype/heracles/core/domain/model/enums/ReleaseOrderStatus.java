package com.poype.heracles.core.domain.model.enums;

/**
 * 发布状态
 */
public enum ReleaseOrderStatus {
    INIT(0, "INIT"),

    PROCESSING(1, "PROCESSING"),

    FINISH(2, "FINISH");

    private int code;

    private String name;

    public static ReleaseOrderStatus getByCode(int code) {
        for (ReleaseOrderStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

    ReleaseOrderStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
