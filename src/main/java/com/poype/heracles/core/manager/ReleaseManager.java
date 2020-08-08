package com.poype.heracles.core.manager;

import com.poype.heracles.core.domain.model.AppOfRelease;
import com.poype.heracles.core.facade.result.ReleaseOrderView;

import java.util.List;

public interface ReleaseManager {

    /**
     * 创建发布单
     * @param sprintId 迭代Id
     * @param releaseName 发布名称
     * @param description 发布描述
     * @param operator 操作者
     * @param envName 环境名称
     * @param appList 发布应用列表
     * @return 发布单Id
     */
    String createReleaseOrder(String sprintId, String releaseName, String description, String operator,
                              String envName, List<AppOfRelease> appList);

    /**
     * 查询发布单状态
     * @param releaseOrderId 发布单Id
     */
    ReleaseOrderView queryReleaseOrderStatus(String releaseOrderId);
}
