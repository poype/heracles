package com.poype.heracles.core.manager.impl;

import com.poype.heracles.core.domain.service.ApplicationService;
import com.poype.heracles.core.manager.ApplicationManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("applicationManager")
public class ApplicationManagerImpl implements ApplicationManager {

    @Resource
    private ApplicationService applicationService;

    @Override
    public String addApplicationBasicInfo(String appName, String appType, String description, String devOwner,
                                          String qaOwner, String belongSystem, String belongBusiness,
                                          String codeRepository, List<String> hostConfigNames) {

        String applicationId = applicationService.addApplicationBasicInfo(appName, appType, description,
                devOwner, qaOwner, belongSystem, belongBusiness, codeRepository, hostConfigNames);
        return applicationId;
    }

    @Override
    public void checkGitAddr(String url) {

    }

}
