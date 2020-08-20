package com.poype.heracles.core.manager.impl;

import com.poype.heracles.core.domain.model.dto.AppOfSprintDto;
import com.poype.heracles.core.domain.model.dto.SimpleSprintDto;
import com.poype.heracles.core.domain.model.sprint.Sprint;
import com.poype.heracles.core.domain.service.EventService;
import com.poype.heracles.core.domain.service.SprintService;
import com.poype.heracles.core.manager.SprintManager;
import com.poype.heracles.core.repository.SprintRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sprintManager")
public class SprintManagerImpl implements SprintManager {

    @Resource
    private SprintService sprintService;

    @Resource
    private SprintRepository sprintRepository;

    @Resource
    private EventService eventService;

    @Override
    public String createNewSprint(String name, String description, String releaseDate,
                                  List<AppOfSprintDto> sprintDtoList, String createUser) {
        // 选择环境
        String envName = "Test1";
        String sprintId = sprintService.createNewSprint(name, description, releaseDate,
                sprintDtoList, createUser, envName);

        eventService.sendSprintCreated(sprintId);
        return sprintId;
    }

    @Override
    public void update(String sprintId, List<AppOfSprintDto> appOfSprintDtoList) {
        Sprint sprint = sprintService.queryBySprintId(sprintId);
        sprintService.updateAppListOfSprint(sprint, appOfSprintDtoList);
    }

    @Override
    public Sprint queryBySprintId(String sprintId) {
        return sprintService.queryBySprintId(sprintId);
    }

    @Override
    public Map<String, Object> querySimpleSprintList(int pageNum) {
        List<SimpleSprintDto> simpleSprintDtoList = sprintRepository.queryPage(pageNum);
        int total = sprintRepository.queryTotal();

        Map<String, Object> map = new HashMap<>();
        map.put("sprintList", simpleSprintDtoList);
        map.put("total", total);
        return map;
    }
}
