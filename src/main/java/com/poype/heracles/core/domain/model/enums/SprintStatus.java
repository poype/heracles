package com.poype.heracles.core.domain.model.enums;

public enum SprintStatus {
    INIT(0, "INIT"),

    START(1, "START"),

    SIT(2, "SIT"),

    UAT(3, "UAT"),

    RC(4, "RC"),

    RELEASE(5, "RELEASE");

    private int code;

    private String name;

    public static SprintStatus getByCode(int code) {
        for (SprintStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

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
