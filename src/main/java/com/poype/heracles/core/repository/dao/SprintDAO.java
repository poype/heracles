package com.poype.heracles.core.repository.dao;

import com.poype.heracles.core.repository.dao.model.AppOfSprintDO;
import com.poype.heracles.core.repository.dao.model.SprintDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface SprintDAO {

    void saveSprint(SprintDO sprintDO);

    void saveAppOfSprint(AppOfSprintDO appOfSprintDO);

    SprintDO querySprintById(@Param("sprintId") String sprintId);

    List<AppOfSprintDO> queryAppOfSprintListById(@Param("sprintId") String sprintId);

    void updateSprintStatus(@Param("sprintId") String sprintId, @Param("targetStatus") int targetStatus);

    void batchUpdateAppOfSprintStatus(@Param("sprintId") String sprintId, @Param("targetStatus") int targetStatus);
}
