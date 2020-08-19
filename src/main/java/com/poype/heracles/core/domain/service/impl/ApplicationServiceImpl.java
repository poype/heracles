package com.poype.heracles.core.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.poype.heracles.core.domain.model.application.JavaApplication;
import com.poype.heracles.core.domain.model.application.config.ApplicationConfig;
import com.poype.heracles.core.domain.model.dto.JavaApplicationDto;
import com.poype.heracles.core.domain.model.enums.ApplicationConfigTypeEnum;
import com.poype.heracles.core.domain.model.enums.ApplicationType;
import com.poype.heracles.core.domain.service.ApplicationService;
import com.poype.heracles.core.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    @Resource
    private ApplicationRepository applicationRepository;

    @Override
    public String createNewApplication(String domainId, String appName, String appType, String description, String devOwner,
                                       List<String> devList, String qaOwner, List<String> qaList, String belongSystem,
                                       String belongBusiness, String codeRepository, JavaApplicationDto javaAppInfo) {
        if (ApplicationType.getByName(appType) == ApplicationType.JAVA) {
            return createJavaApplication(domainId, appName, appType, description, devOwner, devList, qaOwner, qaList,
                    belongSystem, belongBusiness, codeRepository, javaAppInfo);
        }
        return null;
    }

    @Override
    public void updateJavaAppInfo(String appId, JavaApplicationDto javaAppInfo) {
        List<ApplicationConfig> appConfigList = extractJavaConfigList(javaAppInfo);
        applicationRepository.updateJavaAppInfo(appId, javaAppInfo, appConfigList);
    }

    private String createJavaApplication(String domainId, String appName, String appType, String description,
                                         String devOwner, List<String> devList, String qaOwner, List<String> qaList,
                                         String belongSystem, String belongBusiness, String codeRepository,
                                         JavaApplicationDto javaAppInfo) {
        List<ApplicationConfig> appConfigList = extractJavaConfigList(javaAppInfo);

        JavaApplication javaApplication = new JavaApplication(domainId, appName, ApplicationType.getByName(appType),
                description, codeRepository, devOwner, new HashSet<String>(devList), qaOwner,
                new HashSet<String>(qaList), belongSystem, belongBusiness, appConfigList,
                javaAppInfo.getBaseCodeBranch(), javaAppInfo.getConfigFilePath(), javaAppInfo.getJarPath(),
                javaAppInfo.getPomPath(), javaAppInfo.getMvnCommand());

        applicationRepository.addJavaApplication(javaApplication);
        return javaApplication.getApplicationId();
    }

    private List<ApplicationConfig> extractJavaConfigList(JavaApplicationDto javaAppInfo) {
        List<ApplicationConfig> appConfigList = new ArrayList<>();
        // host配置
        if (!CollectionUtils.isEmpty(javaAppInfo.getHostConfigNames())) {
            ApplicationConfig hostConfig = new ApplicationConfig(ApplicationConfigTypeEnum.HOST,
                    JSON.toJSONString(javaAppInfo.getHostConfigNames()));
            appConfigList.add(hostConfig);
        }
        // 硬件配置
        if (!CollectionUtils.isEmpty(javaAppInfo.getHardwareLevels())) {
            ApplicationConfig hardwareConfig = new ApplicationConfig(ApplicationConfigTypeEnum.HARDWARE_LEVEL,
                    JSON.toJSONString(javaAppInfo.getHardwareLevels()));
            appConfigList.add(hardwareConfig);
        }
        return appConfigList;
    }
}
