package com.poype.heracles.core.domain.model.enums;

import org.apache.commons.lang.StringUtils;

public enum EnvironmentType {
    SIT("SIT"),

    UAT("UAT"),

    RC("RC"),

    PROD("PROD");

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

    EnvironmentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
