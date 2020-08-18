package com.poype.heracles.core.manager.impl;

import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.dto.JavaApplicationDto;
import com.poype.heracles.core.domain.model.dto.SimpleApplicationDto;
import com.poype.heracles.core.domain.service.ApplicationService;
import com.poype.heracles.core.manager.ApplicationManager;
import com.poype.heracles.core.repository.ApplicationRepository;
import com.poype.heracles.core.repository.integration.GitClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("applicationManager")
public class ApplicationManagerImpl implements ApplicationManager {

    @Resource
    private ApplicationService applicationService;

    @Resource
    private ApplicationRepository applicationRepository;

    @Resource
    private GitClient gitClient;

    @Override
    public String createNewApplication(String domainId, String appName, String appType, String description,
                                       String devOwner, List<String> devList, String qaOwner, List<String> qaList,
                                       String belongSystem, String belongBusiness, String codeRepository,
                                       JavaApplicationDto javaAppInfo) {
        // 检查代码库地址是否有效
        gitClient.checkGitUrl(codeRepository);

        // 创建应用
        String applicationId = applicationService.createNewApplication(domainId, appName, appType, description,
                devOwner, devList, qaOwner, qaList, belongSystem, belongBusiness, codeRepository, javaAppInfo);

        return applicationId;
    }

    @Override
    public Application queryApplicationDetailById(String appId) {
        Application app = applicationRepository.queryByAppId(appId);
        return app;
    }

    @Override
    public List<SimpleApplicationDto> querySimpleAppList(int pageNum) {
        return applicationRepository.queryPage(pageNum);
    }
}
