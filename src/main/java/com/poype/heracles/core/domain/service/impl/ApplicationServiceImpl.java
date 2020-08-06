package com.poype.heracles.core.domain.service.impl;

import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.service.ApplicationService;
import com.poype.heracles.core.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public String addApplicationBasicInfo(String appName, String appType, String description, String devOwner,
                                          String qaOwner, String belongSystem, String belongBusiness,
                                          String codeRepository, List<String> hostConfigNames) {

        Application application = new Application(appName, appType, description, devOwner, qaOwner,
                belongSystem, belongBusiness, codeRepository);

        applicationRepository.addApplicationBasicInfo(application);

        return application.getApplicationId();
    }
}
