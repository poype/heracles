package com.poype.heracles.core.domain.service;

import com.poype.heracles.core.domain.model.Sprint;

public interface SprintService {

    /**
     * 根据版本Id查询版本信息
     * @param sprintId 版本Id
     * @return 版本模型对象
     */
    Sprint queryBySprintId(String sprintId);
}
