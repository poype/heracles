package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.ReleaseOrder;
import com.poype.heracles.core.domain.model.dto.SimpleReleaseOrderDto;

import java.util.List;

public interface ReleaseRepository {

    void saveReleaseOrder(ReleaseOrder releaseOrder);

    void updateReleaseOrderStatus(ReleaseOrder releaseOrder);

    ReleaseOrder queryByOrderId(String orderId);

    List<SimpleReleaseOrderDto> queryReleaseOrderListBySprintId(String sprintId);
}
