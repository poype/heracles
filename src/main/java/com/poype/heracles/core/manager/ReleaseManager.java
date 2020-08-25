package com.poype.heracles.core.manager;

import com.poype.heracles.core.domain.model.dto.SimpleReleaseOrderDto;
import com.poype.heracles.core.facade.result.ReleaseOrderView;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询一个版本所有发布单简要信息列表
     * @param sprintId 版本Id
     * @return 发布单简要信息列表
     */
    List<SimpleReleaseOrderDto> queryReleaseOrderListBySprintId(String sprintId);

    /**
     * 分页查询所有发布单简要信息列表
     * @param pageNum 页号
     */
    Map<String, Object> queryPageOfReleaseOrder(int pageNum);
}
