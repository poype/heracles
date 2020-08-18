package com.poype.heracles.core.manager;

import com.poype.heracles.core.domain.model.dto.AppOfSprintDto;
import com.poype.heracles.core.domain.model.sprint.Sprint;

import java.util.List;

public interface SprintManager {

    /**
     * 创建一个新的版本
     * @param name 版本名称
     * @param description 版本描述
     * @param releaseDate 发布日期
     * @param sprintDtoList 应用列表
     * @param createUser 创建人
     * @return 版本Id
     */
    String createNewSprint(String name, String description, String releaseDate, List<AppOfSprintDto> sprintDtoList,
                           String createUser);

    /**
     * 根据版本Id查询一个版本信息
     * @param sprintId 版本Id
     * @return 版本模型
     */
    Sprint queryBySprintId(String sprintId);
}
