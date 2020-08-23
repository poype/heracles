package com.poype.heracles.core.domain.service.impl;

import com.poype.heracles.common.enums.BusinessErrorCode;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.core.domain.model.Environment;
import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.dto.AppOfSprintDto;
import com.poype.heracles.core.domain.model.enums.AppOfSprintStatus;
import com.poype.heracles.core.domain.model.enums.EnvironmentStatus;
import com.poype.heracles.core.domain.model.enums.SprintStatus;
import com.poype.heracles.core.domain.model.sprint.AppOfSprint;
import com.poype.heracles.core.domain.model.sprint.Sprint;
import com.poype.heracles.core.domain.service.SprintService;
import com.poype.heracles.core.repository.ApplicationRepository;
import com.poype.heracles.core.repository.EnvironmentRepository;
import com.poype.heracles.core.repository.SprintRepository;
import com.poype.heracles.core.repository.integration.GitClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("sprintService")
public class SprintServiceImpl implements SprintService {

    @Resource
    private SprintRepository sprintRepository;

    @Resource
    private ApplicationRepository applicationRepository;

    @Resource
    private GitClient gitClient;

    @Resource
    private EnvironmentRepository environmentRepository;

    @Override
    public Sprint queryBySprintId(String sprintId) {
        return sprintRepository.queryBySprintId(sprintId);
    }

    @Transactional
    @Override
    public String createNewSprint(String name, String description, String releaseDate,
                                  List<AppOfSprintDto> sprintDtoList, String createUser) {
        Environment environment = environmentRepository.pickOneFreeSitEnv();

        List<AppOfSprint> appList = new ArrayList<>();
        for (AppOfSprintDto appOfSprintDto : sprintDtoList) {
            Application application = applicationRepository.queryByAppName(appOfSprintDto.getAppName());
            AppOfSprint app = new AppOfSprint(appOfSprintDto.getAppName(), appOfSprintDto.getDevList(),
                    appOfSprintDto.getQaList(), appOfSprintDto.getAppName() + releaseDate,
                    application.getCodeRepository(), application.getApplicationType());
            appList.add(app);
        }

        Sprint sprint = new Sprint(name, description, releaseDate, appList, environment.getEnvName());
        environment.setStatus(EnvironmentStatus.BUSY);

        sprintRepository.addNewSprint(sprint);
        environmentRepository.updateEnvironmentStatus(environment, EnvironmentStatus.FREE);
        return sprint.getSprintId();
    }

    @Override
    public void createCodeBranch(String sprintId) {
        Sprint sprint = sprintRepository.queryBySprintId(sprintId);

        for (AppOfSprint appOfSprint : sprint.getApplications()) {
            gitClient.createNewBranch(appOfSprint.getApp(), appOfSprint.getCodeBranch());
        }
        sprintRepository.updateWholeSprintToStartStatus(sprint.getSprintId());
    }

    @Override
    public void updateAppListOfSprint(Sprint sprint, List<AppOfSprintDto> appOfSprintDtoList) {
        List<String> addAppList = new ArrayList<>();
        List<String> updateAppList = new ArrayList<>();
        List<String> removeAppList = new ArrayList<>();

        int originalAppCount = sprint.getApplications().size();
        int newAppCount = appOfSprintDtoList.size();
        for (int i = 0; i < newAppCount; i++) {
            int j = 0;
            for (; j < originalAppCount; j++) {
                AppOfSprint originalApp = sprint.getApplications().get(j);
                AppOfSprintDto newApp = appOfSprintDtoList.get(i);
                if (newApp.getAppName().equals(originalApp.getApp())) {
                    // 版本中已经存在这个应用，需要更新
                    updateAppList.add(newApp.getAppName());
                    break;
                }
            }
            if (j == originalAppCount) {
                // 版本中不包含这个应用，需要增加
                addAppList.add(appOfSprintDtoList.get(i).getAppName());
            }
        }
        for (int i = 0; i < originalAppCount; i++) {
            int j = 0;
            for (; j < newAppCount; j++) {
                AppOfSprint originalApp = sprint.getApplications().get(i);
                AppOfSprintDto newApp = appOfSprintDtoList.get(j);
                if (newApp.getAppName().equals(originalApp.getApp())) {
                    break;
                }
            }
            if (j == newAppCount) {
                // 新的应用列表中没有，是要删除的应用
                removeAppList.add(sprint.getApplications().get(i).getApp());
            }
        }
        for (String app : removeAppList) {
            sprintRepository.deleteApp(app);
        }
        for (String app : updateAppList) {
            for (AppOfSprintDto appOfSprintDto : appOfSprintDtoList) {
                if (app.equals(appOfSprintDto.getAppName())) {
                    sprintRepository.updateDevAndQaOfApp(app, appOfSprintDto.getDevList(),
                            appOfSprintDto.getQaList());
                    break;
                }
            }
        }

        for (String app : addAppList) {
            for (AppOfSprintDto appOfSprintDto : appOfSprintDtoList) {
                if (app.equals(appOfSprintDto.getAppName())) {
                    Application application = applicationRepository.queryByAppName(app);
                    AppOfSprint appOfSprint = new AppOfSprint(appOfSprintDto.getAppName(), appOfSprintDto.getDevList(),
                            appOfSprintDto.getQaList(), appOfSprintDto.getAppName() + sprint.getReleaseDate(),
                            application.getCodeRepository(), application.getApplicationType());
                    sprintRepository.addNewAppForSprint(sprint.getSprintId(), appOfSprint);
                    break;
                }
            }
        }
    }

    @Override
    public String transferAppOfSprintStatus(String sprintId, String app, String status) {
        Sprint sprint = sprintRepository.queryBySprintId(sprintId);
        AppOfSprint appOfSprint = sprint.findAppByName(app);
        AssertUtil.notNull(appOfSprint, BusinessErrorCode.APP_NOT_FOUND);

        appOfSprint.setStatus(AppOfSprintStatus.getByName(status));
        sprintRepository.updateAppOfSprintStatus(appOfSprint);

        // 检查整个sprint的status是否需要扭转
        if (sprint.checkSprintStatusAfterAppStatusTransfer(AppOfSprintStatus.getByName(status))) {
            sprintRepository.updateSprintStatus(sprint.getSprintId(), sprint.getStatus());
        }
        return sprint.getStatus().getName();
    }

    @Override
    public void transferWholeSprintStatus(String sprintId, String status) {
        Sprint sprint = sprintRepository.queryBySprintId(sprintId);
        AssertUtil.isTrue(sprint.getStatus().getCode() > SprintStatus.FINISH_TEST.getCode(),
                BusinessErrorCode.ILLEGAL_SPRINT_STATUS);

        sprint.setStatus(SprintStatus.getByName(status));
        sprintRepository.updateSprintStatus(sprint.getSprintId(), sprint.getStatus());
    }
}
