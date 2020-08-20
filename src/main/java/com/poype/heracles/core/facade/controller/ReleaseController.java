package com.poype.heracles.core.facade.controller;

import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.template.ExecuteCallback;
import com.poype.heracles.common.template.ExecuteTemplate;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import com.poype.heracles.core.facade.request.CreateReleaseOrderForSprintRequest;
import com.poype.heracles.core.facade.request.QueryReleaseOrderStatusRequest;
import com.poype.heracles.core.facade.result.CreateReleaseOrderResult;
import com.poype.heracles.core.facade.result.QueryReleaseOrderStatusResult;
import com.poype.heracles.core.facade.result.ReleaseOrderView;
import com.poype.heracles.core.manager.ReleaseManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

import static com.poype.heracles.common.enums.BusinessErrorCode.PARAM_ILLEGAL;

@Controller
@RequestMapping(value="/release")
public class ReleaseController {

    @Resource
    private ExecuteTemplate executeTemplate;

    @Resource
    private ReleaseManager releaseManager;

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public CreateReleaseOrderResult createReleaseOrderForSprint(final CreateReleaseOrderForSprintRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.RELEASE_ORDER_CREATE);

        final CreateReleaseOrderResult result = new CreateReleaseOrderResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getSprintId(), PARAM_ILLEGAL);
                AssertUtil.notBlank(request.getApp(), PARAM_ILLEGAL);
            }

            @Override
            public void doService() {
                String operator = "liudongliang214";
                String releaseOrderId = releaseManager.createReleaseOrderForSprint(request.getSprintId(),
                        request.getApp(), operator);
                result.setReleaseOrderId(releaseOrderId);
            }
        });
        return result;
    }

    @RequestMapping(value = "/queryStatus", method = RequestMethod.POST, produces = "application/json")
    public QueryReleaseOrderStatusResult queryReleaseStatusByOrderId(final QueryReleaseOrderStatusRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.RELEASE_STATUS_QUERY);

        final QueryReleaseOrderStatusResult result = new QueryReleaseOrderStatusResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getReleaseOrderId(), PARAM_ILLEGAL, "发布单Id不能为空");
            }

            @Override
            public void doService() {
                ReleaseOrderView releaseOrderView = releaseManager.queryReleaseOrderStatus(
                        request.getReleaseOrderId());
                result.setReleaseOrderView(releaseOrderView);
            }
        });

        return result;
    }
}
