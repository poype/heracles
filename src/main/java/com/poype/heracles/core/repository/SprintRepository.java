package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.dto.SimpleSprintDto;
import com.poype.heracles.core.domain.model.enums.SprintStatus;
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
}
