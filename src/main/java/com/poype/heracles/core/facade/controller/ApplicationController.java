package com.poype.heracles.core.facade.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.template.ExecuteCallback;
import com.poype.heracles.common.template.ExecuteTemplate;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.application.JavaApplication;
import com.poype.heracles.core.domain.model.application.config.ApplicationConfig;
import com.poype.heracles.core.domain.model.dto.JavaApplicationDto;
import com.poype.heracles.core.domain.model.dto.SimpleApplicationDto;
import com.poype.heracles.core.domain.model.enums.ApplicationConfigTypeEnum;
import com.poype.heracles.core.domain.model.enums.ApplicationType;
import com.poype.heracles.core.domain.model.enums.EnvironmentType;
import com.poype.heracles.core.domain.model.enums.HardwareLevelEnum;
import com.poype.heracles.core.facade.request.AddApplicationRequest;
import com.poype.heracles.core.facade.request.UpdateJavaAppInfoRequest;
import com.poype.heracles.core.facade.result.CreateApplicationResult;
import com.poype.heracles.core.facade.result.QueryAllAppNameResult;
import com.poype.heracles.core.facade.result.QueryApplicationDetailResult;
import com.poype.heracles.core.facade.result.QueryApplicationSimpleListResult;
import com.poype.heracles.core.manager.ApplicationManager;
import com.poype.heracles.core.repository.integration.GitClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.poype.heracles.common.enums.BusinessErrorCode.PARAM_ILLEGAL;

@Controller
@RequestMapping(value="/application")
public class ApplicationController {

    @Resource
    private ApplicationManager applicationManager;

    @Resource
    private GitClient gitClient;

    @Resource
    private ExecuteTemplate executeTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public CreateApplicationResult addApplication(@RequestBody final AddApplicationRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.ADD_APPLICATION);

        final CreateApplicationResult result = new CreateApplicationResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getAppName(), PARAM_ILLEGAL, "必须填写应用名称");
                AssertUtil.isTrue(ApplicationType.getByName(request.getAppType()) != null, PARAM_ILLEGAL,
                        "无效应用类型");
                AssertUtil.notBlank(request.getDescription(), PARAM_ILLEGAL, "必须填写应用描述");
                AssertUtil.notBlank(request.getDevOwner(), PARAM_ILLEGAL, "必须填写应用开发负责人");
                AssertUtil.notBlank(request.getQaOwner(), PARAM_ILLEGAL, "必须填写应用测试负责人");
                AssertUtil.notBlank(request.getBelongBusiness(), PARAM_ILLEGAL, "必须填写应用所属业务");
                AssertUtil.notBlank(request.getBelongSystem(), PARAM_ILLEGAL, "必须填写应用所属子系统");
                AssertUtil.notBlank(request.getGitUrl(), PARAM_ILLEGAL, "代码仓库地址不能为空");

                if (ApplicationType.getByName(request.getAppType()) == ApplicationType.JAVA) {
                    checkJavaAppInfo(request.getJavaAppInfo());
                }
            }

            @Override
            public void doService() {
                String applicationId = applicationManager.createNewApplication(request.getDomainId(),
                        request.getAppName(), request.getAppType(), request.getDescription(),
                        request.getDevOwner(), request.getDevList(), request.getQaOwner(), request.getQaList(),
                        request.getBelongSystem(), request.getBelongBusiness(), request.getGitUrl(),
                        request.getJavaAppInfo());result.setApplicationId(applicationId);
            }
        });

        return result;
    }

    @RequestMapping(value = "/queryDetail", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QueryApplicationDetailResult queryDetail(@RequestParam("appId") final String appId) {

        ThreadLocalHolder.setBizScene(BizScene.QUERY_APPLICATION_DETAIL);

        final QueryApplicationDetailResult result = new QueryApplicationDetailResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(appId, PARAM_ILLEGAL, "appId不能为空");
            }

            @Override
            public void doService() {
                Application app =
                        applicationManager.queryApplicationDetailById(appId);

                JavaApplication javaApp = (JavaApplication) app;
                JavaApplicationDto javaApplicationDto = new JavaApplicationDto();
                List<ApplicationConfig> configs = app.getConfigs();
                for (ApplicationConfig config : configs) {
                    if (config.getConfigType() == ApplicationConfigTypeEnum.HARDWARE_LEVEL) {
                        Map<String, String> hardwareLevels =
                                JSON.parseObject(config.getConfigValue(), new TypeReference<Map<String, String>>(){});
                        javaApplicationDto.setHardwareLevels(hardwareLevels);
                    } else if (config.getConfigType() == ApplicationConfigTypeEnum.HOST) {
                        List<String> hostConfigNames =
                                JSON.parseArray(config.getConfigValue(), String.class);
                        javaApplicationDto.setHostConfigNames(hostConfigNames);
                    }
                }
                javaApplicationDto.setBaseCodeBranch(javaApp.getBaseCodeBranch());
                javaApplicationDto.setConfigFilePath(javaApp.getConfigFilePath());
                javaApplicationDto.setJarPath(javaApp.getJarPath());
                javaApplicationDto.setPomPath(javaApp.getPomPath());
                javaApplicationDto.setMvnCommand(javaApp.getMvnCommand());

                List<String> devList = new ArrayList<>();
                List<String> qaList = new ArrayList<>();
                devList.addAll(app.getDevSet());
                qaList.addAll(app.getQaSet());

                AddApplicationRequest appView = new AddApplicationRequest(app.getApplicationName(),
                        app.getApplicationType().getName(), app.getDomainId(), app.getDescription(),
                        app.getDevOwner(), app.getQaOwner(), app.getBelongSystem(), app.getBelongBusiness(),
                        app.getCodeRepository(), devList, qaList, javaApplicationDto);
                result.setApp(appView);
            }
        });

        return result;
    }

    @RequestMapping(value = "/querySimpleList", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QueryApplicationSimpleListResult querySimpleList(@RequestParam("pageNum") final int pageNum) {

        ThreadLocalHolder.setBizScene(BizScene.QUERY_APPLICATION_SIMPLE_LIST);

        final QueryApplicationSimpleListResult result = new QueryApplicationSimpleListResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.isTrue(pageNum > 0, PARAM_ILLEGAL);
            }

            @Override
            public void doService() {
                Map<String, Object> map = applicationManager.querySimpleAppList(pageNum);
                result.setAppList((List<SimpleApplicationDto>) map.get("appList"));
                result.setTotal((int) map.get("total"));
            }
        });

        return result;
    }

    @RequestMapping(value = "/checkGitUrl", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public BaseResult checkGitUrl(@RequestParam("gitUrl") final String gitUrl) {

        ThreadLocalHolder.setBizScene(BizScene.CHECK_GIT_URL);

        final BaseResult result = new BaseResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(gitUrl, PARAM_ILLEGAL);
            }

            @Override
            public void doService() {
                gitClient.checkGitUrl(gitUrl);
            }
        });

        return result;
    }

    @RequestMapping(value = "/updateJavaAppInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public BaseResult updateJavaAppInfo(@RequestBody UpdateJavaAppInfoRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.UPDATE_JAVA_APP_INFO);

        final BaseResult result = new BaseResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getAppId(), PARAM_ILLEGAL);
                checkJavaAppInfo(request.getJavaAppInfo());
            }

            @Override
            public void doService() {
                applicationManager.updateJavaAppInfo(request.getAppId(), request.getJavaAppInfo());
            }
        });

        return result;
    }

    @RequestMapping(value = "/queryAllNames", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QueryAllAppNameResult queryAllNames() {

        ThreadLocalHolder.setBizScene(BizScene.QUERY_ALL_APP_NAMES);

        final QueryAllAppNameResult result = new QueryAllAppNameResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void doService() {
                List<String> appNameList = applicationManager.queryAllNames();
                result.setAppNameList(appNameList);
            }
        });

        return result;
    }

    private void checkJavaAppInfo(JavaApplicationDto javaAppInfo) {
        AssertUtil.notNull(javaAppInfo, PARAM_ILLEGAL, "缺少Java应用必要信息");

        Map<String, String> hardwareLevels = javaAppInfo.getHardwareLevels();
        if (!hardwareLevels.isEmpty()) {
            for (Map.Entry<String, String> entry : javaAppInfo.getHardwareLevels().entrySet()) {
                AssertUtil.isTrue(EnvironmentType.getByName(entry.getKey()) != null, PARAM_ILLEGAL,
                        "环境名称不正确");
                AssertUtil.isTrue(HardwareLevelEnum.getByName(entry.getValue()) != null, PARAM_ILLEGAL,
                        "环境级别不合法");
            }
        }
        AssertUtil.notBlank(javaAppInfo.getBaseCodeBranch(), PARAM_ILLEGAL,
                "baseCodeBranch不能为空");
        AssertUtil.notBlank(javaAppInfo.getConfigFilePath(), PARAM_ILLEGAL,
                "configFilePath不能为空");
        AssertUtil.notBlank(javaAppInfo.getJarPath(), PARAM_ILLEGAL,
                "jarPath不能为空");
        AssertUtil.notBlank(javaAppInfo.getPomPath(), PARAM_ILLEGAL,
                "pomPath不能为空");
        AssertUtil.notBlank(javaAppInfo.getMvnCommand(), PARAM_ILLEGAL,
                "mvnCommand不能为空");
    }
}
