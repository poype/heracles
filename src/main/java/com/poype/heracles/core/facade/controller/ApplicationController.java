package com.poype.heracles.core.facade.controller;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.template.ExecuteCallback;
import com.poype.heracles.common.template.ExecuteTemplate;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import com.poype.heracles.core.facade.AddApplicationRequest;
import com.poype.heracles.core.facade.CheckGitAddrRequest;
import com.poype.heracles.core.manager.ApplicationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

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

    @RequestMapping(value = "/check_git", method = RequestMethod.POST, produces = "application/json")
    public BaseResult checkGitAddr(CheckGitAddrRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.CONFIRM_GIT_ADDR);

        final BaseResult result = new BaseResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getGitAddrUrl(), PARAM_ILLEGAL, "Git地址不能为空");
            }

            @Override
            public void doService() {
                applicationManager.checkGitAddr(request.getGitAddrUrl());
            }
        });

        return result;

    }

}
