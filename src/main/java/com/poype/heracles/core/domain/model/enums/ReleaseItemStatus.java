package com.poype.heracles.core.domain.model.enums;

public enum ReleaseItemStatus {
    INIT(0, "INIT"),

    PROCESSING(1, "PROCESSING"),

    SUCCESS(2, "SUCCESS"),

    FAIL(3, "FAIL");

    private int code;

    private String name;

    public static ReleaseItemStatus getByCode(int code) {
        for (ReleaseItemStatus status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null;
    }

    ReleaseItemStatus(int code, String name) {
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
