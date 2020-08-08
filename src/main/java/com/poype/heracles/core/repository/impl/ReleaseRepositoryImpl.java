package com.poype.heracles.core.repository.impl;

import com.poype.heracles.core.domain.model.ReleaseOrder;
import com.poype.heracles.core.repository.ReleaseRepository;
import org.springframework.stereotype.Repository;

@Repository("releaseRepository")
public class ReleaseRepositoryImpl implements ReleaseRepository {

    @Override
    public void saveReleaseOrder(ReleaseOrder releaseOrder) {

    }

    @Override
    public void updateReleaseOrder(ReleaseOrder releaseOrder) {

    }

    @Override
    public ReleaseOrder queryByOrderId(String orderId) {
        return null;
    }
}
