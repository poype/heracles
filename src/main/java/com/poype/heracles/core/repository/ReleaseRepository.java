package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.ReleaseOrder;

public interface ReleaseRepository {

    void saveReleaseOrder(ReleaseOrder releaseOrder);

    void updateReleaseOrder(ReleaseOrder releaseOrder);

    ReleaseOrder queryByOrderId(String orderId);
}
