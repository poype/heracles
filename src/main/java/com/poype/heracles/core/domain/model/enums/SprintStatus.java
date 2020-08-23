package com.poype.heracles.core.domain.model.enums;

public enum SprintStatus {
    INIT(0, "INIT"),

    START(1, "START"),

    SIT(2, "SIT"),

    UAT(3, "UAT"),

    FINISH_TEST(4, "FINISH_TEST"),

    RC(5, "RC"),

    FINISH_RC_TEST(6, "FINISH_RC_TEST"),

    PROD(7, "RELEASE"),

    FINISH_PROD_VERIFY(8, "FINISH_PROD_VERIFY");

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

    public static SprintStatus getByName(String name) {
        for (SprintStatus status : values()) {
            if (status.getName().equals(name)) {
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
