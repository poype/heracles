package com.poype.heracles.common.dto.error;

public enum BizScene {

    /** 添加应用 */
    ADD_APPLICATION("0001", "add application"),

    QUERY_APPLICATION("0002", "confirm git addr"),

    RELEASE_ORDER_CREATE("0003", "create release order"),

    RELEASE_STATUS_QUERY("0004", "query release order status")
    ;

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
