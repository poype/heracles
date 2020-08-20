package com.poype.heracles.core.domain.service;

import com.poype.heracles.core.domain.model.ReleaseOrder;
import com.poype.heracles.core.domain.model.sprint.Sprint;

public interface ReleaseService {
    /**
     * 发布一个版本中的指定应用
     * @param sprint 版本
     * @param app 应用
     * @param operator 操作员
     * @return 发布单Id
     */
    String createReleaseOrderForSprint(Sprint sprint, String app, String operator);

    /**
     * 发布一个版本中的所有应用
     * @param sprint 版本
     * @param operator 操作员
     * @return 发布单Id
     */
    String createReleaseOrderForSprint(Sprint sprint, String operator);


    /**
     * 查询发布单，如果状态不是终态，会执行remote查询
     * @param releaseOrderId 发布单Id
     * @return 发布单模型
     */
    ReleaseOrder queryReleaseOrder(String releaseOrderId);
}
