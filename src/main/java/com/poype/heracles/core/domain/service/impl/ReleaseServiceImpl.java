package com.poype.heracles.core.domain.service.impl;

import com.poype.heracles.common.enums.BusinessErrorCode;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.core.domain.model.AppOfRelease;
import com.poype.heracles.core.domain.model.AppOfSprint;
import com.poype.heracles.core.domain.model.ReleaseOrder;
import com.poype.heracles.core.domain.model.Sprint;
import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.service.ReleaseService;
import com.poype.heracles.core.repository.ApplicationRepository;
import com.poype.heracles.core.repository.ReleaseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("releaseService")
public class ReleaseServiceImpl implements ReleaseService {

    @Resource
    private ReleaseRepository releaseRepository;

    @Resource
    private ApplicationRepository applicationRepository;

    @Override
    public String createReleaseOrderBySprint(Sprint sprint, String releaseName, String description,
                                             String envName, String operator) {
        checkEnvParam(sprint, envName);

        ReleaseOrder releaseOrder = new ReleaseOrder(releaseName, description, envName, operator);

        List<AppOfSprint> appOfSprintList = sprint.getApplications();
        for (AppOfSprint appOfSprint : appOfSprintList) {
            releaseOrder.addAppToRelease(appOfSprint);
        }
        releaseRepository.saveReleaseOrder(releaseOrder);
        return releaseOrder.getOrderId();
    }

    @Override
    public String createReleaseOrderBySprintAndAppList(Sprint sprint, List<String> appList, String releaseName,
                                                       String description, String envName, String operator) {
        checkEnvParam(sprint, envName);

        ReleaseOrder releaseOrder = new ReleaseOrder(releaseName, description, envName, operator);

        List<AppOfSprint> appOfSprintList = sprint.getApplications();
        for (AppOfSprint appOfSprint : appOfSprintList) {
            if (appList.contains(appOfSprint.getApp().getApplicationName())) {
                releaseOrder.addAppToRelease(appOfSprint);
            }
        }
        releaseRepository.saveReleaseOrder(releaseOrder);
        return releaseOrder.getOrderId();
    }

    @Override
    public String createReleaseOrderByAppListAndEnv(List<AppOfRelease> appList, String releaseName,
                                                    String description, String envName, String operator) {
        ReleaseOrder releaseOrder = new ReleaseOrder(releaseName, description, envName, operator);

        for (AppOfRelease appOfRelease : appList) {
            Application application = applicationRepository.queryByAppName(appOfRelease.getAppName());
            releaseOrder.addAppToRelease(application.getApplicationName(), application.getCodeRepository(),
                    appOfRelease.getCodeBranch());
        }
        releaseRepository.saveReleaseOrder(releaseOrder);
        return releaseOrder.getOrderId();
    }


    /**
     * 检查envName参数是否与Sprint匹配
     * @param sprint 版本
     * @param envName 环境名称
     */
    private void checkEnvParam(Sprint sprint, String envName) {
        if (envName.startsWith("TEST")) {
            AssertUtil.isTrue(sprint.getSitEnvName().equals(envName), BusinessErrorCode.RELEASE_ENV_ILLEGAL);
        }
    }
}
