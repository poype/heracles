package com.poype.heracles.core.domain.model.enums;

public enum DataSourceType {
    PG(0, "PG"),

    ORACLE(1, "ORACLE"),

    MYSQL(2, "MYSQL"),
    ;

    private int code;

    private String name;

    public static DataSourceType getByCode(int code) {
        for (DataSourceType type : values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public static DataSourceType getByName(String name) {
        for (DataSourceType type : values()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }

    DataSourceType(int code, String name) {
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
