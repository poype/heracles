package com.poype.heracles.core.repository.dao;

import com.poype.heracles.core.repository.dao.model.EnvironmentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface EnvironmentDAO {

    EnvironmentDO pickOneFreeSitEnv();

    EnvironmentDO queryByName(@Param("envName") String envName);

    int updateStatusInOptimisticLock(@Param("envId") int envId, @Param("targetStatus") int targetStatus,
                                     @Param("originalStatus") int originalStatus);
}
