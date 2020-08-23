package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.Environment;
import com.poype.heracles.core.domain.model.enums.EnvironmentStatus;

public interface EnvironmentRepository {

    /**
     * 根据环境名称查找环境对象
     * @param name 环境名称
     * @return 环境model
     */
    Environment queryByEnvName(String name);

    /**
     * 挑选一个可用的SIT环境
     * @return 环境模型
     */
    Environment pickOneFreeSitEnv();

    /**
     * 更新一个环境的使用状态
     */
    void updateEnvironmentStatus(Environment environment, EnvironmentStatus originalStatus);
}
