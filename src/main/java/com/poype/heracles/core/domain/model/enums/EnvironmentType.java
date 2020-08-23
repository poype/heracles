package com.poype.heracles.core.domain.model.enums;

import org.apache.commons.lang.StringUtils;

public enum EnvironmentType {
    SIT(0, "SIT"),

    UAT(1, "UAT"),

    RC(2, "RC"),

    PROD(3, "PROD");

    private int code;

    private String name;

    public static EnvironmentType getByName(String typeName) {
        if (StringUtils.isBlank(typeName)) {
            return null;
        }
        for (EnvironmentType environmentType : values()) {
            if (StringUtils.equalsIgnoreCase(environmentType.getName(), typeName)) {
                return environmentType;
            }
        }
        return null;
    }

    public static EnvironmentType getByCode(int code) {
        for (EnvironmentType environmentType : values()) {
            if (environmentType.code == code) {
                return environmentType;
            }
        }
        return null;
    }

    EnvironmentType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
