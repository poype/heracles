package com.poype.heracles.core.domain.model;

import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.enums.ApplicationOfEnvironmentStatus;

/**
 * 应用一个环境中的信息
 */
public class ApplicationOfEnvironment {

    /**
     * 应用于环境的关系Id
     */
    private String relationId;

    /**
     * 应用
     */
    private Application application;

    /**
     * 单节点cpu资源
     */
    private double cpuOfNode;

    /**
     * 单节点内存资源
     */
    private int memoryOfNode;

    /**
     * 节点数量
     */
    private int countOfNode;

    /**
     * 应用在该环境下的状态
     */
    private ApplicationOfEnvironmentStatus status;
}
