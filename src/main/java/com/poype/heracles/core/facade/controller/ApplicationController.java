package com.poype.heracles.core.facade.controller;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.template.ExecuteCallback;
import com.poype.heracles.common.template.ExecuteTemplate;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.facade.request.AddApplicationRequest;
import com.poype.heracles.core.facade.request.QueryApplicationRequest;
import com.poype.heracles.core.facade.result.QueryApplicationResult;
import com.poype.heracles.core.manager.ApplicationManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

import java.util.List;

import static com.poype.heracles.common.enums.BusinessErrorCode.PARAM_ILLEGAL;

@Controller
@RequestMapping(value="/application")
public class ApplicationController {

    @Resource
    private ApplicationManager applicationManager;

    @Resource
    private ExecuteTemplate executeTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public BaseResult addApplication(final AddApplicationRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.ADD_APPLICATION);

        final BaseResult result = new BaseResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getAppName(), PARAM_ILLEGAL, "必须填写应用名称");
                AssertUtil.notBlank(request.getAppType(), PARAM_ILLEGAL, "必须填写应用类型");
                AssertUtil.notBlank(request.getDescription(), PARAM_ILLEGAL, "必须填写应用描述");
                AssertUtil.notBlank(request.getDevOwner(), PARAM_ILLEGAL, "必须填写应用开发负责人");
                AssertUtil.notBlank(request.getQaOwner(), PARAM_ILLEGAL, "必须填写应用测试负责人");
                AssertUtil.notBlank(request.getBelongBusiness(), PARAM_ILLEGAL, "必须填写应用所属业务");
                AssertUtil.notBlank(request.getBelongSystem(), PARAM_ILLEGAL, "必须填写应用所属子系统");
                AssertUtil.notBlank(request.getGitUrl(), PARAM_ILLEGAL, "代码仓库地址不能为空");
            }

            @Override
            public void doService() {
                String applicationId = applicationManager.addApplicationBasicInfo(request.getAppName(),
                        request.getAppType(), request.getDescription(), request.getDevOwner(),
                        request.getQaOwner(), request.getBelongSystem(), request.getBelongBusiness(),
                        request.getGitUrl(), request.getHostConfigNames());
                // add applicationId to session
            }
        });

        return result;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json")
    public QueryApplicationResult query(QueryApplicationRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.QUERY_APPLICATION);

        final QueryApplicationResult result = new QueryApplicationResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.isTrue(StringUtils.isNotBlank(request.getAppId()) || request.getPageNum() > 0,
                        PARAM_ILLEGAL, "参数不合法");
            }

            @Override
            public void doService() {
                List<Application> appList =
                        applicationManager.queryApplications(request.getAppId(), request.getPageNum());
                result.setAppList(appList);
            }
        });

        return result;
    }

}
