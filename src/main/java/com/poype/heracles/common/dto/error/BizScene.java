package com.poype.heracles.common.dto.error;

public enum BizScene {

    /** 添加应用 */
    ADD_APPLICATION("0001", "add application"),

    CONFIRM_GIT_ADDR("0002", "confirm git addr");

    /** 场景编码 */
    private final String sceneCode;

    /** 描述说明 */
    private final String description;

    BizScene(String sceneCode, String description) {
        this.sceneCode = sceneCode;
        this.description = description;
    }

    public String getSceneCode() {
        return sceneCode;
    }

    public String getDescription() {
        return description;
    }
}
