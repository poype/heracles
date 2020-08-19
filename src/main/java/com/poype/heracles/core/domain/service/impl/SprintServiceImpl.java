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
            AppOfSprint app = new AppOfSprint(appOfSprintDto.getAppName(), appOfSprintDto.getDevNames(),
                    appOfSprintDto.getQaNames(), appOfSprintDto.getAppName() + releaseDate,
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
}
