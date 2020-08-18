package com.poype.heracles.core.domain.model.enums;

public enum SprintStatus {
    START(0, "START"),

    SIT(1, "SIT"),

    UAT(2, "UAT"),

    RC(3, "RC"),

    RELEASE(4, "RELEASE");

    private int code;

    private String name;

    SprintStatus(int code, String name) {
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
