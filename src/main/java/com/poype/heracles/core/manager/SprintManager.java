package com.poype.heracles.core.manager;

import com.poype.heracles.core.domain.model.dto.AppOfSprintDto;
import com.poype.heracles.core.domain.model.sprint.Sprint;

import java.util.List;
import java.util.Map;

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
     * 更新一个版本信息，只支持增加或减少应用
     * @param sprintId 版本Id
     * @param appOfSprintDtoList 应用list
     */
    void update(String sprintId, List<AppOfSprintDto> appOfSprintDtoList);

    /**
     * 根据版本Id查询一个版本信息
     * @param sprintId 版本Id
     * @return 版本模型
     */
    Sprint queryBySprintId(String sprintId);

    /**
     * 分页查询应用的简要信息
     * @return 分页号
     */
    Map<String, Object> querySimpleSprintList(int pageNum);
}
