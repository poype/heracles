package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.HostConfig;

public interface HostConfigRepository {

    HostConfig queryByConfigName(String configName);
}
