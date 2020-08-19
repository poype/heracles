package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.application.JavaApplication;
import com.poype.heracles.core.domain.model.application.config.ApplicationConfig;
import com.poype.heracles.core.domain.model.dto.JavaApplicationDto;
import com.poype.heracles.core.domain.model.dto.SimpleApplicationDto;

import java.util.List;

public interface ApplicationRepository {

    /**
     * 添加java应用
     * @param javaApplication java应用模型
     */
    void addJavaApplication(JavaApplication javaApplication);

    /**
     * 根据应用名字查询应用模型
     * @param appName 应用名字
     * @return 对应的应用模型
     */
    Application queryByAppName(String appName);

    /**
     * 根据应用Id查询应用列表
     * @param appId 应用Id
     * @return 应用对象
     */
    Application queryByAppId(String appId);

    /**
     * 查询一页记录
     * @param pageNum 页号
     * @return 应用列表
     */
    List<SimpleApplicationDto> queryPage(int pageNum);

    /**
     * 更新应用的Java部分信息
     * @param appId 应用Id
     * @param javaAppInfo java相关信息
     */
    void updateJavaAppInfo(String appId, JavaApplicationDto javaAppInfo, List<ApplicationConfig> appConfigList);

    /**
     * 总数
     */
    int queryTotal();
}
