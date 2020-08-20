package com.poype.heracles.core.manager.impl;

import com.poype.heracles.common.constant.Constants;
import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.application.JavaApplication;
import com.poype.heracles.core.domain.model.enums.ApplicationType;
import com.poype.heracles.core.domain.service.ConfigFileService;
import com.poype.heracles.core.manager.ConfigFileManager;
import com.poype.heracles.core.repository.ApplicationRepository;
import com.poype.heracles.core.repository.HostConfigRepository;
import com.poype.heracles.core.repository.integration.GitClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("configFileManager")
public class ConfigFileManagerImpl implements ConfigFileManager {

    @Resource
    private ApplicationRepository applicationRepository;

    @Resource
    private ConfigFileService configFileService;

    @Resource
    private GitClient gitClient;

    @Resource
    private HostConfigRepository hostConfigRepository;

    @Override
    public void generateConfigFileForApp(String appId) {
        Application application = applicationRepository.queryByAppId(appId);

        gitClient.pull(Constants.operationProjectName);
        if (application.getApplicationType() == ApplicationType.JAVA) {
            configFileService.generateConfigFileForJavaApp((JavaApplication) application);
        }
        gitClient.commitAndPush(Constants.operationProjectName);
    }

    @Override
    public List<String> queryAllHostNames() {
        return hostConfigRepository.queryAllHostNames();
    }
}
