package com.poype.heracles.core.manager.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.application.JavaApplication;
import com.poype.heracles.core.domain.model.application.config.ApplicationConfig;
import com.poype.heracles.core.domain.model.dto.JavaApplicationDto;
import com.poype.heracles.core.domain.model.dto.SimpleApplicationDto;
import com.poype.heracles.core.domain.model.enums.ApplicationConfigTypeEnum;
import com.poype.heracles.core.domain.model.enums.ApplicationType;
import com.poype.heracles.core.domain.service.ApplicationService;
import com.poype.heracles.core.domain.service.EventService;
import com.poype.heracles.core.facade.request.AddApplicationRequest;
import com.poype.heracles.core.manager.ApplicationManager;
import com.poype.heracles.core.repository.ApplicationRepository;
import com.poype.heracles.core.repository.integration.GitClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("applicationManager")
public class ApplicationManagerImpl implements ApplicationManager {

    @Resource
    private ApplicationService applicationService;

    @Resource
    private ApplicationRepository applicationRepository;

    @Resource
    private GitClient gitClient;

    @Resource
    private EventService eventService;

    @Override
    public String createNewApplication(String domainId, String appName, String appType, String description,
                                       String devOwner, List<String> devList, String qaOwner, List<String> qaList,
                                       String belongSystem, String belongBusiness, String codeRepository,
                                       JavaApplicationDto javaAppInfo) {
        // 检查代码库地址是否有效
        gitClient.checkGitUrl(codeRepository);

        // 允许不增加研发组员和测试组员
        if (devList == null) {
            devList = new ArrayList<>();
        }
        if (qaList == null) {
            qaList = new ArrayList<>();
        }
        // 创建应用
        String applicationId = applicationService.createNewApplication(domainId, appName, appType, description,
                devOwner, devList, qaOwner, qaList, belongSystem, belongBusiness, codeRepository, javaAppInfo);

        // 异步触发应用配置文件的创建
        eventService.sendApplicationCreated(applicationId);
        return applicationId;
    }

    @Override
    public AddApplicationRequest queryApplicationDetailById(String appId) {
        Application app = applicationRepository.queryByAppId(appId);

        if (app.getApplicationType() == ApplicationType.JAVA) {
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

            List<String> devList = new ArrayList<>(app.getDevSet());
            List<String> qaList = new ArrayList<>(app.getQaSet());

            return new AddApplicationRequest(app.getApplicationName(),
                    app.getApplicationType().getName(), app.getDomainId(), app.getDescription(),
                    app.getDevOwner(), app.getQaOwner(), app.getBelongSystem(), app.getBelongBusiness(),
                    app.getCodeRepository(), devList, qaList, javaApplicationDto);
        }
        return null;
    }

    @Override
    public Map<String, Object> querySimpleAppList(int pageNum) {
        List<SimpleApplicationDto> simpleApplicationDtoList = applicationRepository.queryPage(pageNum);
        int total = applicationRepository.queryTotal();

        Map<String, Object> map = new HashMap<>();
        map.put("appList", simpleApplicationDtoList);
        map.put("total", total);
        return map;
    }

    @Override
    public void updateJavaAppInfo(String appId, JavaApplicationDto javaAppInfo) {
        applicationService.updateJavaAppInfo(appId, javaAppInfo);
    }

    @Override
    public List<String> queryAllNames() {
        return applicationRepository.queryAllNames();
    }
}
