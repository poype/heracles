package com.poype.heracles.core.facade.controller;

import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.template.ExecuteCallback;
import com.poype.heracles.common.template.ExecuteTemplate;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import com.poype.heracles.core.domain.model.AppOfRelease;
import com.poype.heracles.core.facade.request.CreateReleaseOrderRequest;
import com.poype.heracles.core.facade.result.CreateReleaseOrderResult;
import com.poype.heracles.core.manager.ReleaseManager;
import org.apache.commons.lang.StringUtils;
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
    public CreateReleaseOrderResult createReleaseOrder(final CreateReleaseOrderRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.ADD_APPLICATION);

        final CreateReleaseOrderResult result = new CreateReleaseOrderResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getEnvName(), PARAM_ILLEGAL, "环境名字不能为空");
                AssertUtil.notBlank(request.getReleaseName(), PARAM_ILLEGAL, "发布名称不能为空");
                AssertUtil.notBlank(request.getDescription(), PARAM_ILLEGAL, "发布描述不能为空");

                if (StringUtils.isBlank(request.getSprintId())) {
                    AssertUtil.notEmpty(request.getAppList(), PARAM_ILLEGAL, "应用列表不能为空");
                    for (AppOfRelease app : request.getAppList()) {
                        AssertUtil.notBlank(app.getAppName(), PARAM_ILLEGAL, "应用名字不能为空");
                        AssertUtil.notBlank(app.getCodeBranch(), PARAM_ILLEGAL, "代码分支不能为空");
                    }
                }
            }

            @Override
            public void doService() {
                String operator = "liudongliang214"; // todo 从session中获取
                String releaseOrderId = releaseManager.createReleaseOrder(
                        request.getSprintId(), request.getReleaseName(), request.getDescription(),
                        operator, request.getEnvName(), request.getAppList());
                result.setReleaseOrderId(releaseOrderId);
            }
        });
        return result;
    }


}
