package com.poype.heracles.core.domain.service.impl;

import com.poype.heracles.common.enums.BusinessErrorCode;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.core.domain.model.*;
import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.enums.AppOfSprintStatus;
import com.poype.heracles.core.domain.model.enums.ReleaseItemStatus;
import com.poype.heracles.core.domain.model.enums.ReleaseOrderStatus;
import com.poype.heracles.core.domain.model.enums.SprintStatus;
import com.poype.heracles.core.domain.model.sprint.AppOfSprint;
import com.poype.heracles.core.domain.model.sprint.Sprint;
import com.poype.heracles.core.domain.service.ReleaseService;
import com.poype.heracles.core.repository.ApplicationRepository;
import com.poype.heracles.core.repository.ReleaseRepository;
import com.poype.heracles.core.repository.SprintRepository;
import com.poype.heracles.core.repository.integration.ReleaseItemClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("releaseService")
public class ReleaseServiceImpl implements ReleaseService {

    @Resource
    private ReleaseRepository releaseRepository;

    @Resource
    private ApplicationRepository applicationRepository;

    @Resource
    private ReleaseItemClient releaseItemClient;

    @Resource
    private SprintRepository sprintRepository;

    @Override
    public String createReleaseOrderForSprint(Sprint sprint, String app, String operator) {
        AppOfSprint appOfSprint = findAppFromSprint(sprint, app);
        AssertUtil.notNull(appOfSprint, BusinessErrorCode.PARAM_ILLEGAL);

        // 默认环境是UAT
        String envName = AppOfSprintStatus.UAT.getName();
        if (appOfSprint.getStatus() == AppOfSprintStatus.SIT) {
            envName = sprint.getSitEnvName();
        }

        String releaseName = sprint.getSprintName() + "-" + app;
        String description = sprint.getSprintName() + "-" + app + "-" + envName + "-release";

        ReleaseOrder releaseOrder = new ReleaseOrder(releaseName, description, envName, operator);

        Application application = applicationRepository.queryByAppName(appOfSprint.getApp());
        releaseOrder.addAppToRelease(appOfSprint, application.getCodeRepository());

        releaseRepository.saveReleaseOrder(releaseOrder);
        return releaseOrder.getOrderId();
    }

    @Override
    public String createReleaseOrderForSprint(Sprint sprint, String operator) {
        String envName = "";
        if (sprint.getStatus().getCode() < SprintStatus.FINISH_RC_TEST.getCode()) {
            // 在完成RC验证之前，只能发布RC环境
            envName = "RC";
            sprint.setStatus(SprintStatus.RC);
        } else if (sprint.getStatus().getCode() < SprintStatus.FINISH_PROD_VERIFY.getCode()) {
            envName = "PROD";
            sprint.setStatus(SprintStatus.PROD);
        }
        AssertUtil.notBlank(envName, BusinessErrorCode.PARAM_ILLEGAL);

        String releaseName = sprint.getSprintName() + "-" + envName;
        String description = sprint.getSprintName() + "-" + envName + "-whole-release";

        ReleaseOrder releaseOrder = new ReleaseOrder(releaseName, description, envName, operator);

        for (AppOfSprint appOfSprint : sprint.getApplications()) {
            Application application = applicationRepository.queryByAppName(appOfSprint.getApp());
            releaseOrder.addAppToRelease(appOfSprint, application.getCodeRepository());
        }

        // 更新sprint状态
        sprintRepository.updateSprintStatus(sprint.getSprintId(), sprint.getStatus());
        releaseRepository.saveReleaseOrder(releaseOrder);

        return releaseOrder.getOrderId();
    }

    @Override
    public ReleaseOrder queryReleaseOrder(String releaseOrderId) {
        ReleaseOrder releaseOrder = releaseRepository.queryByOrderId(releaseOrderId);
        if (releaseOrder.getStatus() == ReleaseOrderStatus.PROCESSING) {
            List<ReleaseItem> releaseItems = releaseOrder.getReleaseItems();
            boolean changeFlag = false;
            for (ReleaseItem releaseItem : releaseItems) {
                if (releaseItem.getStatus() == ReleaseItemStatus.PROCESSING) {
                    // 远端查询最新状态
                    ReleaseItemStatus status = releaseItemClient.queryStatus(releaseItem);

                    if (status == ReleaseItemStatus.SUCCESS || status == ReleaseItemStatus.FAIL) {
                        releaseOrder.updateItemStatus(releaseItem.getItemId(), status);
                        changeFlag = true;
                    }
                }
            }
            if (changeFlag) {
                releaseRepository.updateReleaseOrderStatus(releaseOrder);
            }
        }
        return releaseOrder;
    }

    private AppOfSprint findAppFromSprint(Sprint sprint, String app) {
        for (AppOfSprint appOfSprint : sprint.getApplications()) {
            if (appOfSprint.getApp().equals(app)) {
                return appOfSprint;
            }
        }
        return null;
    }
}
