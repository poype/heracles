package com.poype.heracles.core.domain.service;

import com.poype.heracles.core.domain.model.dto.AppOfSprintDto;
import com.poype.heracles.core.domain.model.sprint.Sprint;

import java.util.List;

public interface SprintService {

    /**
     * 根据版本Id查询版本信息
     * @param sprintId 版本Id
     * @return 版本模型对象
     */
    Sprint queryBySprintId(String sprintId);

    /**
     * 创建一个新的版本
     * @param name 版本名称
     * @param description 版本描述
     * @param releaseDate 发版日期
     * @param sprintDtoList 版本应用列表
     * @param createUser 创建者
     * @param sitEnvName 分配的sit环境
     * @return 版本Id
     */
    String createNewSprint(String name, String description, String releaseDate,
                           List<AppOfSprintDto> sprintDtoList, String createUser, String sitEnvName);

    /**
     * 为一个版本中的所有应用创建分支
     * @param sprintId 版本Id
     */
    void createCodeBranch(String sprintId);
}
