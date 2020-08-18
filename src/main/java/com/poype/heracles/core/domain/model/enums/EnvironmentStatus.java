package com.poype.heracles.core.domain.model.enums;

public enum EnvironmentStatus {

    FREE(0, "FREE", "环境空闲"),

    BUSY(1, "BUSY", "环境被占用");

    private int code;

    private String name;

    private String description;

    EnvironmentStatus(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
