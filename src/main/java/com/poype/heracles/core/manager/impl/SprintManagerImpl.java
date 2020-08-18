package com.poype.heracles.core.manager.impl;

import com.poype.heracles.core.domain.model.dto.AppOfSprintDto;
import com.poype.heracles.core.domain.model.sprint.Sprint;
import com.poype.heracles.core.domain.service.EventService;
import com.poype.heracles.core.domain.service.SprintService;
import com.poype.heracles.core.manager.SprintManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sprintManager")
public class SprintManagerImpl implements SprintManager {

    @Resource
    private SprintService sprintService;

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
    public Sprint queryBySprintId(String sprintId) {
        return sprintService.queryBySprintId(sprintId);
    }
}
