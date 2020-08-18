package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.sprint.Sprint;

public interface SprintRepository {

    void addNewSprint(Sprint sprint);

    Sprint queryBySprintId(String sprintId);

    void updateAppOfSprintStatus(Sprint sprint);
}
