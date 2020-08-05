package com.poype.heracles.core.domain.model.enums;

/**
 * 发布状态
 */
public enum ReleaseBaseOrderStatus {
    ;

    private int code;

    private String name;

    ReleaseBaseOrderStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
