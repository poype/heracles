package com.poype.heracles.core.domain.service.impl;

import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.domain.model.dto.AppOfSprintDto;
import com.poype.heracles.core.domain.model.sprint.AppOfSprint;
import com.poype.heracles.core.domain.model.sprint.Sprint;
import com.poype.heracles.core.domain.service.SprintService;
import com.poype.heracles.core.repository.ApplicationRepository;
import com.poype.heracles.core.repository.SprintRepository;
import com.poype.heracles.core.repository.integration.GitClient;
import org.springframework.stereotype.Service;

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

    @Override
    public Sprint queryBySprintId(String sprintId) {
        return sprintRepository.queryBySprintId(sprintId);
    }

    @Override
    public String createNewSprint(String name, String description, String releaseDate,
                                  List<AppOfSprintDto> sprintDtoList, String createUser, String sitEnvName) {

        List<AppOfSprint> appList = new ArrayList<>();
        for (AppOfSprintDto appOfSprintDto : sprintDtoList) {
            Application application = applicationRepository.queryByAppName(appOfSprintDto.getAppName());
            AppOfSprint app = new AppOfSprint(appOfSprintDto.getAppName(), appOfSprintDto.getDevList(),
                    appOfSprintDto.getQaList(), appOfSprintDto.getAppName() + releaseDate,
                    application.getCodeRepository(), application.getApplicationType());
            appList.add(app);
        }

        Sprint sprint = new Sprint(name, description, releaseDate, appList, sitEnvName);
        sprintRepository.addNewSprint(sprint);

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
}
