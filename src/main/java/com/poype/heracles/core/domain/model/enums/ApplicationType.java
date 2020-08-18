package com.poype.heracles.core.domain.model.enums;

import org.apache.commons.lang.StringUtils;

public enum ApplicationType {
    JAVA(0, "JAVA");

    private int code;

    private String name;

    public static ApplicationType getByCode(int code) {
        for (ApplicationType type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public static ApplicationType getByName(String typeName) {
        if (StringUtils.isBlank(typeName)) {
            return null;
        }
        for (ApplicationType applicationType : values()) {
            if (StringUtils.equalsIgnoreCase(applicationType.getName(), typeName)) {
                return applicationType;
            }
        }
        return null;
    }

    ApplicationType(int code, String name) {
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
