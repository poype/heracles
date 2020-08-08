package com.poype.heracles.core.domain.model.enums;

/**
 * 发布状态
 */
public enum ReleaseOrderStatus {
    INIT(0, "INIT");

    private int code;

    private String name;

    ReleaseOrderStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
