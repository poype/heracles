package com.poype.heracles.core.repository.impl;

import com.alibaba.fastjson.JSON;
import com.poype.heracles.core.domain.model.dto.SimpleSprintDto;
import com.poype.heracles.core.domain.model.enums.AppOfSprintStatus;
import com.poype.heracles.core.domain.model.enums.ApplicationType;
import com.poype.heracles.core.domain.model.enums.SprintStatus;
import com.poype.heracles.core.domain.model.sprint.AppOfSprint;
import com.poype.heracles.core.domain.model.sprint.Sprint;
import com.poype.heracles.core.repository.SprintRepository;
import com.poype.heracles.core.repository.dao.SprintDAO;
import com.poype.heracles.core.repository.dao.model.AppOfSprintDO;
import com.poype.heracles.core.repository.dao.model.SprintDO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("sprintRepository")
public class SprintRepositoryImpl implements SprintRepository {

    @Resource
    private SprintDAO sprintDAO;

    @Transactional
    @Override
    public void addNewSprint(Sprint sprint) {
        SprintDO sprintDO = new SprintDO(sprint.getSprintId(), sprint.getSprintName(), sprint.getDescription(),
                sprint.getReleaseDate(), sprint.getStatus().getCode(), sprint.getSitEnvName());

        sprintDAO.saveSprint(sprintDO);

        for (AppOfSprint appOfSprint : sprint.getApplications()) {
            AppOfSprintDO appOfSprintDO = new AppOfSprintDO(appOfSprint.getRelationId(), sprint.getSprintId(),
                    appOfSprint.getApp(), appOfSprint.getAppType().getCode(), appOfSprint.getCodeRepository(),
                    appOfSprint.getCodeBranch(), JSON.toJSONString(appOfSprint.getDevList()),
                    JSON.toJSONString(appOfSprint.getQaList()), appOfSprint.getStatus().getCode());
            sprintDAO.saveAppOfSprint(appOfSprintDO);
        }
    }

    @Override
    public Sprint queryBySprintId(String sprintId) {
        SprintDO sprintDO = sprintDAO.querySprintById(sprintId);
        List<AppOfSprintDO> appOfSprintDOList = sprintDAO.queryAppOfSprintListById(sprintId);

        List<AppOfSprint> appList = new ArrayList<>();
        for (AppOfSprintDO appOfSprintDO : appOfSprintDOList) {
            AppOfSprint app = new AppOfSprint(appOfSprintDO.getRelationId(),
                    appOfSprintDO.getApp(),
                    ApplicationType.getByCode(appOfSprintDO.getAppType()),
                    appOfSprintDO.getCodeRepository(),
                    appOfSprintDO.getCodeBranch(),
                    JSON.parseObject(appOfSprintDO.getDevS(), List.class),
                    JSON.parseObject(appOfSprintDO.getQaS(), List.class),
                    AppOfSprintStatus.getByCode(appOfSprintDO.getStatus()));
            appList.add(app);
        }
        return new Sprint(sprintDO.getSprintId(), sprintDO.getSprintName(), sprintDO.getDescription(),
                sprintDO.getReleaseDate(), SprintStatus.getByCode(sprintDO.getStatus()), appList,
                sprintDO.getSitEnvName());
    }

    @Transactional
    @Override
    public void updateWholeSprintToStartStatus(String sprintId) {
        sprintDAO.updateSprintStatus(sprintId, SprintStatus.START.getCode());
        sprintDAO.batchUpdateAppOfSprintStatus(sprintId, AppOfSprintStatus.START.getCode());
    }

    @Override
    public List<SimpleSprintDto> queryPage(int pageNum) {
        int offset = (pageNum - 1) * 10;

        List<SimpleSprintDto> simpleSprintDtoList = new ArrayList<>();

        List<SprintDO> sprintDOList = sprintDAO.queryPageOfSprint(offset);
        for (SprintDO sprintDO : sprintDOList) {
            SimpleSprintDto simpleSprintDto = new SimpleSprintDto(sprintDO.getSprintId(), sprintDO.getSprintName(),
                    sprintDO.getDescription(), sprintDO.getReleaseDate(),
                    SprintStatus.getByCode(sprintDO.getStatus()).getName());
            simpleSprintDtoList.add(simpleSprintDto);
        }
        return simpleSprintDtoList;
    }

    @Override
    public int queryTotal() {
        return sprintDAO.queryTotal();
    }

    @Override
    public void updateSprintStatus(String sprintId, SprintStatus targetStatus) {
        sprintDAO.updateSprintStatus(sprintId, targetStatus.getCode());
    }

    @Override
    public void deleteApp(String appName) {
        sprintDAO.deleteApp(appName);
    }

    @Override
    public void updateDevAndQaOfApp(String appName, List<String> devList, List<String> qaList) {
        sprintDAO.updateDevAndQaOfApp(appName, JSON.toJSONString(devList), JSON.toJSONString(qaList));
    }

    @Override
    public void addNewAppForSprint(String sprintId, AppOfSprint appOfSprint) {
        AppOfSprintDO appOfSprintDO = new AppOfSprintDO(appOfSprint.getRelationId(), sprintId,
                appOfSprint.getApp(), appOfSprint.getAppType().getCode(), appOfSprint.getCodeRepository(),
                appOfSprint.getCodeBranch(), JSON.toJSONString(appOfSprint.getDevList()),
                JSON.toJSONString(appOfSprint.getQaList()), appOfSprint.getStatus().getCode());
        sprintDAO.saveAppOfSprint(appOfSprintDO);
    }

    @Override
    public void updateAppOfSprintStatus(AppOfSprint appOfSprint) {
        sprintDAO.updateAppOfSprintStatus(appOfSprint.getRelationId(), appOfSprint.getStatus().getCode());
    }
}
