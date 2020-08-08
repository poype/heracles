package com.poype.heracles.core.manager.impl;

import com.poype.heracles.core.domain.model.AppOfRelease;
import com.poype.heracles.core.domain.model.Sprint;
import com.poype.heracles.core.domain.service.EventService;
import com.poype.heracles.core.domain.service.ReleaseService;
import com.poype.heracles.core.domain.service.SprintService;
import com.poype.heracles.core.manager.ReleaseManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("releaseManager")
public class ReleaseManagerImpl implements ReleaseManager {

    @Resource
    private SprintService sprintService;

    @Resource
    private ReleaseService releaseService;

    @Resource
    private EventService eventService;

    @Override
    public String createReleaseOrder(String sprintId, String releaseName, String description, String operator,
                                     String envName, List<AppOfRelease> appList) {
        String releaseOrderId;

        if (StringUtils.isNotBlank(sprintId)) {
            Sprint sprint = sprintService.queryBySprintId(sprintId);
            if (CollectionUtils.isEmpty(appList)) {
                // 发布整个sprint相关的所有app
                releaseOrderId = releaseService.createReleaseOrderBySprint(sprint, releaseName,
                        description, envName, operator);
            } else {
                // 发布sprint中对应的app
                List<String> appNames = new ArrayList<>();
                for (AppOfRelease appOfRelease : appList) {
                    appNames.add(appOfRelease.getAppName());
                }
                releaseOrderId = releaseService.createReleaseOrderBySprintAndAppList(sprint, appNames,
                        releaseName, description, envName, operator);
            }
        } else {
            // 跟sprint无关，把相关应用发到对应的环境上
            releaseOrderId = releaseService.createReleaseOrderByAppListAndEnv(appList, releaseName,
                    description, envName, operator);
        }
        // 发送消息，异步推进发布状态
        eventService.sendReleaseOrderCreated(releaseOrderId);

        return releaseOrderId;
    }
}
