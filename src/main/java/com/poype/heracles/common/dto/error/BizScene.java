package com.poype.heracles.common.dto.error;

public enum BizScene {

    /** 添加应用 */
    ADD_APPLICATION("0001", "add application"),

    QUERY_APPLICATION_DETAIL("0002", "confirm git addr"),

    RELEASE_ORDER_CREATE("0003", "create release order"),

    RELEASE_STATUS_QUERY("0004", "query release order status"),

    CREATE_NEW_SPRINT("0005", "create new sprint"),

    GENERATE_CONFIG_FILE_FOR_APP("0006", "generate config file for app"),

    CREATE_NEW_BRANCH("0007", "create new code branch"),

    QUERY_SPRINT_DETAIL("0008", "query sprint detail"),

    QUERY_APPLICATION_SIMPLE_LIST("0009", "query simple list"),

    CHECK_GIT_URL("0010", "check git url"),

    UPDATE_JAVA_APP_INFO("0011", "update java app info"),

    SPRINT_QUERY_SIMPLE_LIST("0012", "sprint query simple list"),

    QUERY_ALL_APP_NAMES("0013", "query all app names"),

    UPDATE_SPRINT("0014", "update sprint"),

    QUERY_ALL_HOST("0015", "query all host"),

    QUERY_SIMPLE_RELEASE_OF_SPRINT("0016", "query all host"),

    TRANSFER_APP_OF_SPRINT_STATUS("0017", "transfer app of sprint status"),

    TRANSFER_WHOLE_SPRINT_STATUS("0018", "transfer whole sprint status"),

    QUERY_ALL_ENV("0019", "query all environment"),

    UPDATE_ENV("0020", "update env"),
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
