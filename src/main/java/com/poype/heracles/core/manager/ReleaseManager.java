package com.poype.heracles.core.manager;

import com.poype.heracles.core.facade.result.ReleaseOrderView;

public interface ReleaseManager {

    /**
     * 创建发布单
     * @param sprintId 迭代Id
     * @param operator 操作者
     * @param app 发布应用
     * @return 发布单Id
     */
    String createReleaseOrderForSprint(String sprintId, String app, String operator);

    /**
     * 创建发布单发布一个版本中的所有应用，只支持RC和PROD两个环境
     * @param sprintId 版本Id
     * @param operator 操作者
     * @return 发布单Id
     */
    String createReleaseOrderForSprint(String sprintId, String operator);

    /**
     * 查询发布单状态
     * @param releaseOrderId 发布单Id
     */
    ReleaseOrderView queryReleaseOrderStatus(String releaseOrderId);
}
