package com.poype.heracles.core.repository.dao;

import com.poype.heracles.core.repository.dao.model.EnvironmentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface EnvironmentDAO {

    EnvironmentDO pickOneFreeSitEnv();

    EnvironmentDO queryByName(@Param("envName") String envName);

    EnvironmentDO queryById(@Param("envId") int envId);

    int updateStatusInOptimisticLock(@Param("envId") int envId, @Param("targetStatus") int targetStatus,
                                     @Param("originalStatus") int originalStatus);

    void save(EnvironmentDO environmentDO);

    List<EnvironmentDO> queryAll();

    void updateEnvironment(@Param("envId") int envId, @Param("defaultCpuOfApp") int defaultCpuOfApp,
                           @Param("defaultMemoryOfApp") int defaultMemoryOfApp);
}
