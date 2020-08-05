package com.poype.heracles.core.domain.model.enums;

/**
 * 应用在某个环境中的状态
 */
public enum ApplicationOfEnvironmentStatus {
    ;

    private int code;

    private String name;

    ApplicationOfEnvironmentStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
