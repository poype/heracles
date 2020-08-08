package com.poype.heracles.core.repository.integration.impl;

import com.poype.heracles.core.domain.model.ReleaseItem;
import com.poype.heracles.core.domain.model.enums.ReleaseItemStatus;
import com.poype.heracles.core.repository.integration.ReleaseItemClient;
import org.springframework.stereotype.Repository;

@Repository("releaseItemClient")
public class ReleaseItemClientImpl implements ReleaseItemClient {

    @Override
    public ReleaseItemStatus queryStatus(ReleaseItem item) {
        return null;
    }
}
