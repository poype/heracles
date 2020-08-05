package com.poype.common.dto.error;

public enum BizScene {

    /** 商户注册 */
    MERCHANT_REGISTER("0001", "merchant register"),

    /** 修改商户信息 */
    UPDATE_MERCHANT("0002", "modify merchant");

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
