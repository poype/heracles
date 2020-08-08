package com.poype.heracles.core.domain.model.enums;

public enum ReleaseItemStatus {
    INIT(0, "INIT");

    private int code;

    private String name;

    ReleaseItemStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }
}
