package com.poype.heracles.core.repository.impl;

import com.alibaba.fastjson.JSON;
import com.poype.heracles.common.enums.BusinessErrorCode;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.application.JavaApplication;
import com.poype.heracles.core.domain.model.application.config.ApplicationConfig;
import com.poype.heracles.core.domain.model.dto.JavaApplicationDto;
import com.poype.heracles.core.domain.model.dto.SimpleApplicationDto;
import com.poype.heracles.core.domain.model.enums.ApplicationConfigTypeEnum;
import com.poype.heracles.core.domain.model.enums.ApplicationType;
import com.poype.heracles.core.repository.ApplicationRepository;
import com.poype.heracles.core.repository.dao.ApplicationDAO;
import com.poype.heracles.core.repository.dao.model.ApplicationConfigDO;
import com.poype.heracles.core.repository.dao.model.ApplicationDO;
import com.poype.heracles.core.repository.dao.model.JavaApplicationDO;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Repository("applicationRepository")
public class ApplicationRepositoryImpl implements ApplicationRepository {

    @Resource
    private ApplicationDAO applicationDAO;

    @Transactional
    @Override
    public void addJavaApplication(JavaApplication javaApplication) {
        ApplicationDO applicationDO = convertToApplicationDO(javaApplication);
        JavaApplicationDO javaApplicationDO = convertToJavaApplicationDO(javaApplication);
        List<ApplicationConfigDO> applicationConfigDOList = extractApplicationConfigDOList(javaApplication);

        try {
            for (ApplicationConfigDO applicationConfigDO : applicationConfigDOList) {
                applicationDAO.saveApplicationConfig(applicationConfigDO);
            }
            applicationDAO.saveApplication(applicationDO);
            applicationDAO.saveJavaApplication(javaApplicationDO);
        } catch (DuplicateKeyException ex) {
            AssertUtil.isTrue(false, BusinessErrorCode.DUPLICATE_APP_NAME);
        }
    }

    @Override
    public Application queryByAppName(String appName) {
        ApplicationDO applicationDO = applicationDAO.queryApplicationByName(appName);
        JavaApplicationDO javaApplicationDO =
                applicationDAO.queryJavaApplicationById(applicationDO.getApplicationId());
        List<ApplicationConfigDO> configDOList =
                applicationDAO.queryConfigListByAppId(applicationDO.getApplicationId());

        return recoverJavaApplicationModel(applicationDO, javaApplicationDO, configDOList);
    }

    @Override
    public Application queryByAppId(String appId) {
        ApplicationDO applicationDO = applicationDAO.queryApplicationById(appId);
        JavaApplicationDO javaApplicationDO = applicationDAO.queryJavaApplicationById(appId);
        List<ApplicationConfigDO> configDOList = applicationDAO.queryConfigListByAppId(appId);

        return recoverJavaApplicationModel(applicationDO, javaApplicationDO, configDOList);
    }

    @Override
    public List<SimpleApplicationDto> queryPage(int pageNum) {
        int offset = (pageNum - 1) * 10;

        List<SimpleApplicationDto> applicationList = new ArrayList<>();

        List<ApplicationDO> applicationDOList = applicationDAO.queryPageOfApplication(offset);
        for (ApplicationDO applicationDO : applicationDOList) {
            SimpleApplicationDto simpleApplicationDto = new SimpleApplicationDto(applicationDO.getApplicationId(),
                    applicationDO.getDomainId(), applicationDO.getApplicationName(),
                    ApplicationType.getByCode(applicationDO.getApplicationType()).getName(),
                    applicationDO.getBelongSystem(), applicationDO.getBelongBusiness());
            applicationList.add(simpleApplicationDto);
        }
        return applicationList;
    }

    @Override
    public void updateJavaAppInfo(String appId, JavaApplicationDto javaAppInfo) {
        JavaApplicationDO javaApplicationDO = applicationDAO.queryJavaApplicationById(appId);
        AssertUtil.notNull(javaApplicationDO, BusinessErrorCode.APP_NOT_FOUND);

        javaApplicationDO.setBaseCodeBranch(javaAppInfo.getBaseCodeBranch());
        javaApplicationDO.setConfigFilePath(javaAppInfo.getConfigFilePath());
        javaApplicationDO.setJarPath(javaAppInfo.getJarPath());
        javaApplicationDO.setPomPath(javaAppInfo.getPomPath());
        javaApplicationDO.setMvnCommand(javaAppInfo.getMvnCommand());

        applicationDAO.updateJavaApplication(javaApplicationDO);
    }

    private JavaApplication recoverJavaApplicationModel(ApplicationDO applicationDO,
                                                        JavaApplicationDO javaApplicationDO,
                                                        List<ApplicationConfigDO> configDOList) {
        List<ApplicationConfig> configList = new ArrayList<>();
        for (ApplicationConfigDO configDO : configDOList) {
            ApplicationConfig config = new ApplicationConfig(configDO.getConfigId(),
                    ApplicationConfigTypeEnum.getByCode(configDO.getConfigType()),
                    configDO.getConfigValue());
            configList.add(config);
        }
        JavaApplication javaApplication = new JavaApplication(applicationDO.getDomainId(),
                applicationDO.getApplicationName(), ApplicationType.getByCode(applicationDO.getApplicationType()),
                applicationDO.getDescription(), applicationDO.getCodeRepository(), applicationDO.getDevOwner(),
                JSON.parseObject(applicationDO.getDevs(), Set.class), applicationDO.getQaOwner(),
                JSON.parseObject(applicationDO.getQas(), Set.class), applicationDO.getBelongSystem(),
                applicationDO.getBelongBusiness(), configList, javaApplicationDO.getBaseCodeBranch(),
                javaApplicationDO.getConfigFilePath(), javaApplicationDO.getJarPath(), javaApplicationDO.getPomPath(),
                javaApplicationDO.getMvnCommand());
        return javaApplication;
    }

    private ApplicationDO convertToApplicationDO(Application application) {
        ApplicationDO applicationDO = new ApplicationDO();
        applicationDO.setApplicationId(application.getApplicationId());
        applicationDO.setDomainId(application.getDomainId());
        applicationDO.setApplicationName(application.getApplicationName());
        applicationDO.setApplicationType(application.getApplicationType().getCode());
        applicationDO.setDescription(application.getDescription());
        applicationDO.setCodeRepository(application.getCodeRepository());
        applicationDO.setDevOwner(application.getDevOwner());
        applicationDO.setDevs(JSON.toJSONString(application.getDevSet()));
        applicationDO.setQaOwner(application.getQaOwner());
        applicationDO.setQas(JSON.toJSONString(application.getQaSet()));
        applicationDO.setBelongSystem(application.getBelongSystem());
        applicationDO.setBelongBusiness(application.getBelongBusiness());
        return applicationDO;
    }

    private JavaApplicationDO convertToJavaApplicationDO(JavaApplication javaApplication) {
        JavaApplicationDO javaApplicationDO = new JavaApplicationDO();
        javaApplicationDO.setApplicationId(javaApplication.getApplicationId());
        javaApplicationDO.setBaseCodeBranch(javaApplication.getBaseCodeBranch());
        javaApplicationDO.setConfigFilePath(javaApplication.getConfigFilePath());
        javaApplicationDO.setJarPath(javaApplication.getJarPath());
        javaApplicationDO.setPomPath(javaApplication.getPomPath());
        javaApplicationDO.setMvnCommand(javaApplication.getMvnCommand());
        return javaApplicationDO;
    }

    private List<ApplicationConfigDO> extractApplicationConfigDOList(Application application) {
        List<ApplicationConfigDO> applicationConfigDOList = new ArrayList<>();
        for (ApplicationConfig applicationConfig : application.getConfigs()) {
            ApplicationConfigDO applicationConfigDO = new ApplicationConfigDO();
            applicationConfigDO.setConfigId(applicationConfig.getConfigId());
            applicationConfigDO.setApplicationId(application.getApplicationId());
            applicationConfigDO.setConfigType(applicationConfig.getConfigType().getCode());
            applicationConfigDO.setConfigValue(applicationConfig.getConfigValue());
            applicationConfigDOList.add(applicationConfigDO);
        }
        return applicationConfigDOList;
    }
}
