package com.poype.heracles.core.repository.impl;

import com.poype.heracles.core.domain.model.HostConfig;
import com.poype.heracles.core.repository.HostConfigRepository;
import org.springframework.stereotype.Repository;

@Repository("hostConfigRepository")
public class HostConfigRepositoryImpl implements HostConfigRepository {

    @Override
    public HostConfig queryByConfigName(String configName) {
        return null;
    }
}
