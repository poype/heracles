package com.poype.heracles.core.domain.service;

import java.util.List;

public interface ApplicationService {

    String addApplicationBasicInfo(String appName, String appType, String description, String devOwner,
                                   String qaOwner, String belongSystem, String belongBusiness, String codeRepository,
                                   List<String> hostConfigNames);
}
