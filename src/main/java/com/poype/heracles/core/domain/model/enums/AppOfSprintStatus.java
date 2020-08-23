package com.poype.heracles.core.domain.model.enums;

public enum AppOfSprintStatus {
    INIT(0, "INIT"),

    START(1, "START"),

    SIT(2, "SIT"),

    UAT(3, "UAT"),

    FINISH(4, "FINISH");

    private int code;

    private String name;

    public static AppOfSprintStatus getByCode(int code) {
        for (AppOfSprintStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

    public static AppOfSprintStatus getByName(String name) {
        for (AppOfSprintStatus status : values()) {
            if (status.getName().equals(name)) {
                return status;
            }
        }
        return null;
    }

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
