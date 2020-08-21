package com.poype.heracles.core.manager.impl;

import com.poype.heracles.common.enums.BusinessErrorCode;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.core.domain.model.ReleaseItem;
import com.poype.heracles.core.domain.model.ReleaseOrder;
import com.poype.heracles.core.domain.model.dto.SimpleReleaseOrderDto;
import com.poype.heracles.core.domain.model.enums.SprintStatus;
import com.poype.heracles.core.domain.model.sprint.Sprint;
import com.poype.heracles.core.domain.service.EventService;
import com.poype.heracles.core.domain.service.ReleaseService;
import com.poype.heracles.core.domain.service.SprintService;
import com.poype.heracles.core.facade.result.ReleaseItemView;
import com.poype.heracles.core.facade.result.ReleaseOrderView;
import com.poype.heracles.core.manager.ReleaseManager;
import com.poype.heracles.core.repository.ReleaseRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service("releaseManager")
public class ReleaseManagerImpl implements ReleaseManager {

    @Resource
    private SprintService sprintService;

    @Resource
    private ReleaseService releaseService;

    @Resource
    private ReleaseRepository releaseRepository;

    @Resource
    private EventService eventService;

    @Override
    public String createReleaseOrderForSprint(String sprintId, String app, String operator) {
        Sprint sprint = sprintService.queryBySprintId(sprintId);
        String releaseOrderId = releaseService.createReleaseOrderForSprint(sprint, app, operator);

        // 发送消息，异步推进发布状态
        eventService.sendReleaseOrderCreated(releaseOrderId);

        return releaseOrderId;
    }

    @Override
    public String createReleaseOrderForSprint(String sprintId, String operator) {
        Sprint sprint = sprintService.queryBySprintId(sprintId);

        // 只有RC和PROD两个环境可以整体发布
        AssertUtil.isTrue(sprint.getStatus().getCode() >= SprintStatus.FINISH_TEST.getCode() &&
                        sprint.getStatus().getCode() <= SprintStatus.PROD.getCode(),
                        BusinessErrorCode.ILLEGAL_SPRINT_STATUS);

        String releaseOrderId = releaseService.createReleaseOrderForSprint(sprint, operator);

        // 发送消息，异步推进发布状态
        eventService.sendReleaseOrderCreated(releaseOrderId);

        return releaseOrderId;
    }

    @Override
    public ReleaseOrderView queryReleaseOrderStatus(String releaseOrderId) {
        ReleaseOrder releaseOrder = releaseService.queryReleaseOrder(releaseOrderId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        ReleaseOrderView releaseOrderView = new ReleaseOrderView(releaseOrder.getOrderId(),
                releaseOrder.getReleaseName(), releaseOrder.getDescription(), releaseOrder.getEnvName(),
                releaseOrder.getStatus().getName(), releaseOrder.getOperator(),
                formatter.format(releaseOrder.getReleaseDate()));

        List<ReleaseItemView> itemViewList = new ArrayList<>();
        for (ReleaseItem item : releaseOrder.getReleaseItems()) {
            ReleaseItemView itemView = new ReleaseItemView(item.getItemId(), item.getAppName(),
                    item.getCodeRepos(), item.getCodeBranch(), item.getStatus().getName());
            itemViewList.add(itemView);
        }
        releaseOrderView.setItemList(itemViewList);
        return releaseOrderView;
    }

    @Override
    public List<SimpleReleaseOrderDto> queryReleaseOrderListBySprintId(String sprintId) {
        return releaseRepository.queryReleaseOrderListBySprintId(sprintId);
    }
}
