package com.poype.heracles.core.domain.model.enums;

public enum HostOperationType {
    ;

    private int code;

    private String name;

    private String description;

    HostOperationType(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }
}
