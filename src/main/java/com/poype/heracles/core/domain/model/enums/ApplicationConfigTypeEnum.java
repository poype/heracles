package com.poype.heracles.core.domain.model.enums;

public enum ApplicationConfigTypeEnum {
    HOST(0, "HOST"),

    HARDWARE_LEVEL(1, "HARDWARE_LEVEL");

    private int code;

    private String name;

    ApplicationConfigTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ApplicationConfigTypeEnum getByCode(int code) {
        for (ApplicationConfigTypeEnum type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
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

