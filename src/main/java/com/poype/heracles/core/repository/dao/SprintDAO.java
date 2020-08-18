package com.poype.heracles.core.repository.dao;

import com.poype.heracles.core.repository.dao.model.AppOfSprintDO;
import com.poype.heracles.core.repository.dao.model.SprintDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface SprintDAO {

    void saveSprint(SprintDO sprintDO);

    void saveAppOfSprint(AppOfSprintDO appOfSprintDO);
}
