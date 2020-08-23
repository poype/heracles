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
        List<ApplicationConfigDO> applicationConfigDOList =
                extractApplicationConfigDOList(javaApplication.getApplicationId(), javaApplication.getConfigs());

        try {
            applicationDAO.saveApplication(applicationDO);
            applicationDAO.saveJavaApplication(javaApplicationDO);
            if (applicationConfigDOList.size() > 0) {
                applicationDAO.saveApplicationConfigList(applicationConfigDOList);
            }
        } catch (DuplicateKeyException ex) {
            AssertUtil.isTrue(false, BusinessErrorCode.DUPLICATE_APP_NAME);
        }
    }

    @Override
    public Application queryByAppName(String appName) {
        ApplicationDO applicationDO = applicationDAO.queryApplicationByName(appName);
        AssertUtil.notNull(applicationDO, BusinessErrorCode.APP_NOT_FOUND);

        if (ApplicationType.getByCode(applicationDO.getApplicationType()) == ApplicationType.JAVA) {
            return recoverJavaApplicationModel(applicationDO);
        }
        return null;
    }

    @Override
    public Application queryByAppId(String appId) {
        ApplicationDO applicationDO = applicationDAO.queryApplicationById(appId);
        AssertUtil.notNull(applicationDO, BusinessErrorCode.APP_NOT_FOUND);

        if (ApplicationType.getByCode(applicationDO.getApplicationType()) == ApplicationType.JAVA) {
            return recoverJavaApplicationModel(applicationDO);
        }
        return null;
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

    @Transactional
    @Override
    public void updateJavaAppInfo(String appId, JavaApplicationDto javaAppInfo, List<ApplicationConfig> appConfigList) {
        JavaApplicationDO javaApplicationDO = applicationDAO.queryJavaApplicationById(appId);
        AssertUtil.notNull(javaApplicationDO, BusinessErrorCode.APP_NOT_FOUND);

        javaApplicationDO.setBaseCodeBranch(javaAppInfo.getBaseCodeBranch());
        javaApplicationDO.setConfigFilePath(javaAppInfo.getConfigFilePath());
        javaApplicationDO.setJarPath(javaAppInfo.getJarPath());
        javaApplicationDO.setPomPath(javaAppInfo.getPomPath());
        javaApplicationDO.setMvnCommand(javaAppInfo.getMvnCommand());

        applicationDAO.updateJavaApplication(javaApplicationDO);
        applicationDAO.deleteConfigsByAppId(appId);

        List<ApplicationConfigDO> applicationConfigDOList = extractApplicationConfigDOList(appId, appConfigList);
        if (applicationConfigDOList.size() > 0) {
            applicationDAO.saveApplicationConfigList(applicationConfigDOList);
        }
    }

    @Override
    public int queryTotal() {
        return applicationDAO.queryTotal();
    }

    @Override
    public List<String> queryAllNames() {
        return applicationDAO.queryAllNames();
    }


    private JavaApplication recoverJavaApplicationModel(ApplicationDO applicationDO) {
        JavaApplicationDO javaApplicationDO =
                applicationDAO.queryJavaApplicationById(applicationDO.getApplicationId());
        AssertUtil.notNull(javaApplicationDO, BusinessErrorCode.APP_NOT_FOUND,
                "JAVA APP INFO NOT EXIST");

        List<ApplicationConfigDO> configDOList =
                applicationDAO.queryConfigListByAppId(applicationDO.getApplicationId());


        List<ApplicationConfig> configList = new ArrayList<>();
        for (ApplicationConfigDO configDO : configDOList) {
            ApplicationConfig config = new ApplicationConfig(configDO.getConfigId(),
                    ApplicationConfigTypeEnum.getByCode(configDO.getConfigType()),
                    configDO.getConfigValue());
            configList.add(config);
        }
        return new JavaApplication(applicationDO.getDomainId(),
                applicationDO.getApplicationName(), ApplicationType.getByCode(applicationDO.getApplicationType()),
                applicationDO.getDescription(), applicationDO.getCodeRepository(), applicationDO.getDevOwner(),
                JSON.parseObject(applicationDO.getDevS(), Set.class), applicationDO.getQaOwner(),
                JSON.parseObject(applicationDO.getQaS(), Set.class), applicationDO.getBelongSystem(),
                applicationDO.getBelongBusiness(), configList, javaApplicationDO.getBaseCodeBranch(),
                javaApplicationDO.getConfigFilePath(), javaApplicationDO.getJarPath(), javaApplicationDO.getPomPath(),
                javaApplicationDO.getMvnCommand());
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
        applicationDO.setDevS(JSON.toJSONString(application.getDevSet()));
        applicationDO.setQaOwner(application.getQaOwner());
        applicationDO.setQaS(JSON.toJSONString(application.getQaSet()));
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

    private List<ApplicationConfigDO> extractApplicationConfigDOList(String applicationId,
                                                                     List<ApplicationConfig> configs) {
        List<ApplicationConfigDO> applicationConfigDOList = new ArrayList<>();
        for (ApplicationConfig applicationConfig : configs) {
            ApplicationConfigDO applicationConfigDO = new ApplicationConfigDO();
            applicationConfigDO.setConfigId(applicationConfig.getConfigId());
            applicationConfigDO.setApplicationId(applicationId);
            applicationConfigDO.setConfigType(applicationConfig.getConfigType().getCode());
            applicationConfigDO.setConfigValue(applicationConfig.getConfigValue());
            applicationConfigDOList.add(applicationConfigDO);
        }
        return applicationConfigDOList;
    }
}
