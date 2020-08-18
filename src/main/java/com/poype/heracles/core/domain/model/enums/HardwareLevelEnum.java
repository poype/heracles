package com.poype.heracles.core.domain.model.enums;

import org.apache.commons.lang.StringUtils;

public enum HardwareLevelEnum {

    LOW(0, "LOW", 1, 2048, "1核-2G"),

    MIDDLE(1, "MIDDLE", 2, 4096, "2核-4G"),

    HIGH(2, "HIGH", 4, 8192, "4核-8G"),

    SUPER(3, "SUPER", 8, 16384, "8核-16G");

    private int code;

    private String name;

    private int cpu;

    private int memory;

    private String description;

    public static HardwareLevelEnum getByName(String levelName) {
        if (StringUtils.isBlank(levelName)) {
            return null;
        }
        for (HardwareLevelEnum level : values()) {
            if (StringUtils.equalsIgnoreCase(level.getName(), levelName)) {
                return level;
            }
        }
        return null;
    }

    HardwareLevelEnum(int code, String name, int cpu, int memory, String description) {
        this.code = code;
        this.name = name;
        this.cpu = cpu;
        this.memory = memory;
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

    public int getCpu() {
        return cpu;
    }

    public void setCpu(int cpu) {
        this.cpu = cpu;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
