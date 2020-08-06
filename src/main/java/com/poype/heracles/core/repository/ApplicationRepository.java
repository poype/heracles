package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.application.Application;

public interface ApplicationRepository {

    /**
     * 添加应用基本信息
     * @param application 应用模型
     */
    void addApplicationBasicInfo(Application application);
}
