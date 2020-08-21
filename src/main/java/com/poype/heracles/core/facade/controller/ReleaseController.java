package com.poype.heracles.core.facade.controller;

import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.template.ExecuteCallback;
import com.poype.heracles.common.template.ExecuteTemplate;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import com.poype.heracles.core.domain.model.dto.SimpleReleaseOrderDto;
import com.poype.heracles.core.facade.request.CreateReleaseOrderForSprintRequest;
import com.poype.heracles.core.facade.result.CreateReleaseOrderResult;
import com.poype.heracles.core.facade.result.QueryReleaseOrderStatusResult;
import com.poype.heracles.core.facade.result.QuerySimpleReleaseOrderOfSprintResult;
import com.poype.heracles.core.facade.result.ReleaseOrderView;
import com.poype.heracles.core.manager.ReleaseManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.List;

import static com.poype.heracles.common.enums.BusinessErrorCode.PARAM_ILLEGAL;

@Controller
@RequestMapping(value="/release")
public class ReleaseController {

    @Resource
    private ExecuteTemplate executeTemplate;

    @Resource
    private ReleaseManager releaseManager;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public CreateReleaseOrderResult createReleaseOrderForSprint(
            @RequestBody final CreateReleaseOrderForSprintRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.RELEASE_ORDER_CREATE);

        final CreateReleaseOrderResult result = new CreateReleaseOrderResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getSprintId(), PARAM_ILLEGAL);
            }

            @Override
            public void doService() {
                String operator = "liudongliang214";

                String releaseOrderId;
                if (StringUtils.isBlank(request.getApp())) {
                    releaseOrderId = releaseManager.createReleaseOrderForSprint(request.getSprintId(), operator);
                } else {
                    releaseOrderId = releaseManager.createReleaseOrderForSprint(request.getSprintId(),
                            request.getApp(), operator);
                }
                result.setReleaseOrderId(releaseOrderId);
            }
        });
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/queryStatus", method = RequestMethod.GET, produces = "application/json")
    public QueryReleaseOrderStatusResult queryReleaseStatusByOrderId(
            @RequestParam("releaseOrderId") final String releaseOrderId) {

        ThreadLocalHolder.setBizScene(BizScene.RELEASE_STATUS_QUERY);

        final QueryReleaseOrderStatusResult result = new QueryReleaseOrderStatusResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(releaseOrderId, PARAM_ILLEGAL, "发布单Id不能为空");
            }

            @Override
            public void doService() {
                ReleaseOrderView releaseOrderView = releaseManager.queryReleaseOrderStatus(releaseOrderId);
                result.setReleaseOrder(releaseOrderView);
            }
        });

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/queryReleaseListOfSprint", method = RequestMethod.GET, produces = "application/json")
    public QuerySimpleReleaseOrderOfSprintResult querySimpleReleaseOrderListBySprintId(
            @RequestParam("sprintId") final String sprintId) {

        ThreadLocalHolder.setBizScene(BizScene.QUERY_SIMPLE_RELEASE_OF_SPRINT);

        final QuerySimpleReleaseOrderOfSprintResult result = new QuerySimpleReleaseOrderOfSprintResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(sprintId, PARAM_ILLEGAL, "sprintId不能为空");
            }

            @Override
            public void doService() {
                List<SimpleReleaseOrderDto> simpleReleaseOrderDtoList =
                        releaseManager.queryReleaseOrderListBySprintId(sprintId);

                result.setReleaseList(simpleReleaseOrderDtoList);
            }
        });

        return result;
    }
}
