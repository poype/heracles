package com.poype.heracles.core.domain.model.enums;

public enum AppOfSprintStatus {
    INIT(0, "INIT"),

    DEV(1, "DEV"),

    SIT(2, "SIT"),

    UAT(3, "UAT"),

    FINISH(4, "FINISH");

    private int code;

    private String name;

    AppOfSprintStatus(int code, String name) {
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
