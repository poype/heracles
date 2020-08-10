package com.poype.heracles.core.manager.impl;

import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.service.ApplicationService;
import com.poype.heracles.core.manager.ApplicationManager;
import com.poype.heracles.core.repository.ApplicationRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("applicationManager")
public class ApplicationManagerImpl implements ApplicationManager {

    @Resource
    private ApplicationService applicationService;

    @Resource
    private ApplicationRepository applicationRepository;

    @Override
    public String addApplicationBasicInfo(String appName, String appType, String description, String devOwner,
                                          String qaOwner, String belongSystem, String belongBusiness,
                                          String codeRepository, List<String> hostConfigNames) {

        String applicationId = applicationService.addApplicationBasicInfo(appName, appType, description,
                devOwner, qaOwner, belongSystem, belongBusiness, codeRepository, hostConfigNames);
        return applicationId;
    }

    @Override
    public List<Application> queryApplications(String appId, int pageNum) {
        if (StringUtils.isNotBlank(appId)) {
            Application app = applicationRepository.queryByAppId(appId);
            List<Application> appList = new ArrayList<>();
            appList.add(app);
            return appList;
        } else {
            return applicationRepository.queryPage(pageNum);
        }
    }
}
