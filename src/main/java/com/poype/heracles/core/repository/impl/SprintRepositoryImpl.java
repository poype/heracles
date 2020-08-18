package com.poype.heracles.core.repository.impl;

import com.alibaba.fastjson.JSON;
import com.poype.heracles.core.domain.model.sprint.AppOfSprint;
import com.poype.heracles.core.domain.model.sprint.Sprint;
import com.poype.heracles.core.repository.SprintRepository;
import com.poype.heracles.core.repository.dao.SprintDAO;
import com.poype.heracles.core.repository.dao.model.AppOfSprintDO;
import com.poype.heracles.core.repository.dao.model.SprintDO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("sprintRepository")
public class SprintRepositoryImpl implements SprintRepository {

    @Resource
    private SprintDAO sprintDAO;

    @Override
    public void addNewSprint(Sprint sprint) {
        SprintDO sprintDO = new SprintDO(sprint.getSprintId(), sprint.getSprintName(), sprint.getDescription(),
                sprint.getReleaseDate(), sprint.getStatus().getCode(), sprint.getSitEnvName());

        sprintDAO.saveSprint(sprintDO);

        for (AppOfSprint appOfSprint : sprint.getApplications()) {
            AppOfSprintDO appOfSprintDO = new AppOfSprintDO(appOfSprint.getRelationId(), sprint.getSprintId(),
                    appOfSprint.getApp(), appOfSprint.getAppType().getCode(), appOfSprint.getCodeRepository(),
                    appOfSprint.getCodeBranch(), JSON.toJSONString(appOfSprint.getDevSet()),
                    JSON.toJSONString(appOfSprint.getQaSet()), appOfSprint.getStatus().getCode());
            sprintDAO.saveAppOfSprint(appOfSprintDO);
        }
    }

    @Override
    public Sprint queryBySprintId(String sprintId) {
        return null;
    }

    @Override
    public void updateAppOfSprintStatus(Sprint sprint) {

    }
}
