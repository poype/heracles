package com.poype.heracles.core.repository.integration;

import com.poype.heracles.core.domain.model.ReleaseItem;
import com.poype.heracles.core.domain.model.enums.ReleaseItemStatus;

public interface ReleaseItemClient {

    ReleaseItemStatus queryStatus(ReleaseItem item);
}
