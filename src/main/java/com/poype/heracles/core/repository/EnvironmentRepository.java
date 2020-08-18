package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.Environment;

public interface EnvironmentRepository {

    /**
     * 根据环境名称查找环境对象
     * @param name 环境名称
     * @return 环境model
     */
    Environment queryByEnvName(String name);
}
