package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.dto.SimpleSprintDto;
import com.poype.heracles.core.domain.model.enums.SprintStatus;
import com.poype.heracles.core.domain.model.sprint.AppOfSprint;
import com.poype.heracles.core.domain.model.sprint.Sprint;

import java.util.List;

public interface SprintRepository {

    void addNewSprint(Sprint sprint);

    Sprint queryBySprintId(String sprintId);

    void updateWholeSprintToStartStatus(String sprintId);

    List<SimpleSprintDto> queryPage(int pageNum);

    /**
     * 总数
     */
    int queryTotal();

    void updateSprintStatus(String sprintId, SprintStatus targetStatus);

    void deleteApp(String appName);

    void updateDevAndQaOfApp(String appName, List<String> devList, List<String> qaList);

    void addNewAppForSprint(String sprintId, AppOfSprint appOfSprint);
}
