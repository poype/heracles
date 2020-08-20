package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.ReleaseOrder;

public interface ReleaseRepository {

    void saveReleaseOrder(ReleaseOrder releaseOrder);

    void updateReleaseOrderStatus(ReleaseOrder releaseOrder);

    ReleaseOrder queryByOrderId(String orderId);
}
