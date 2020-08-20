package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.HostConfig;

import java.util.List;

public interface HostConfigRepository {

    HostConfig queryByConfigName(String configName);

    List<String> queryAllHostNames();
}
