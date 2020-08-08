package com.poype.heracles.core.domain.model.enums;

public enum AppOfSprintStatus {
    ;

    private int code;

    private String name;

    private String description;

    AppOfSprintStatus(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }
}
