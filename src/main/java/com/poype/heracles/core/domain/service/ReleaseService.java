package com.poype.heracles.core.domain.service;

import com.poype.heracles.core.domain.model.AppOfRelease;
import com.poype.heracles.core.domain.model.Sprint;

import java.util.List;

public interface ReleaseService {

    /**
     * 发布一次迭代中的所有应用
     * @param sprint 版本
     * @param releaseName 发布名称
     * @param description 发布描述
     * @param envName 环境名字
     * @param operator 操作员
     * @return 发布单Id
     */
    String createReleaseOrderBySprint(Sprint sprint, String releaseName, String description,
                                      String envName, String operator);

    /**
     * 发布一个版本中的指定应用
     * @param sprint 版本
     * @param appList 应用列表
     * @param releaseName 发布名称
     * @param description 发布描述
     * @param envName 环境名字
     * @param operator 操作员
     * @return 发布单Id
     */
    String createReleaseOrderBySprintAndAppList(Sprint sprint, List<String> appList, String releaseName,
                                                String description, String envName, String operator);

    /**
     * 将对应的应用发布到指定的环境下
     * @param appList 应用列表
     * @param releaseName 发布名称
     * @param description 发布描述
     * @param envName 环境名字
     * @param operator 操作员
     * @return 发布单Id
     */
    String createReleaseOrderByAppListAndEnv(List<AppOfRelease> appList, String releaseName,
                                             String description, String envName, String operator);
}
