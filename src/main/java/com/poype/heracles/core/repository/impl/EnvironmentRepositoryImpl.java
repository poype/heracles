package com.poype.heracles.core.repository.impl;

import com.poype.heracles.common.enums.BusinessErrorCode;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.core.domain.model.Environment;
import com.poype.heracles.core.domain.model.enums.EnvironmentStatus;
import com.poype.heracles.core.domain.model.enums.EnvironmentType;
import com.poype.heracles.core.repository.EnvironmentRepository;
import com.poype.heracles.core.repository.dao.EnvironmentDAO;
import com.poype.heracles.core.repository.dao.model.EnvironmentDO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("environmentRepository")
public class EnvironmentRepositoryImpl implements EnvironmentRepository {

    @Resource
    private EnvironmentDAO environmentDAO;

    @Override
    public Environment queryByEnvName(String name) {
        EnvironmentDO envDO = environmentDAO.queryByName(name);
        AssertUtil.notNull(envDO, BusinessErrorCode.ENV_NOT_EXIST);

        return new Environment(envDO.getEnvId(), envDO.getEnvName(),
                EnvironmentType.getByCode(envDO.getEnvType()), envDO.getDefaultCpuOfApp(),
                envDO.getDefaultMemoryOfApp(), envDO.getTotalCpu(), envDO.getTotalMemory(),
                EnvironmentStatus.getByCode(envDO.getStatus()));
    }

    @Override
    public Environment pickOneFreeSitEnv() {
        EnvironmentDO envDO = environmentDAO.pickOneFreeSitEnv();
        AssertUtil.notNull(envDO, BusinessErrorCode.NO_FREE_SIT_ENV);

        return new Environment(envDO.getEnvId(), envDO.getEnvName(),
                EnvironmentType.getByCode(envDO.getEnvType()), envDO.getDefaultCpuOfApp(),
                envDO.getDefaultMemoryOfApp(), envDO.getTotalCpu(), envDO.getTotalMemory(),
                EnvironmentStatus.getByCode(envDO.getStatus()));
    }

    @Override
    public void updateEnvironmentStatus(Environment environment, EnvironmentStatus originalStatus) {
        int count = environmentDAO.updateStatusInOptimisticLock(environment.getEnvId(),
                environment.getStatus().getCode(), originalStatus.getCode());

        AssertUtil.isTrue(count == 1, BusinessErrorCode.PICK_ENV_CONFLICT);
    }

    @Override
    public void saveEnvironment(Environment environment) {
        EnvironmentDO environmentDO = new EnvironmentDO(environment.getEnvName(),
                environment.getEnvType().getCode(), environment.getDefaultCpuOfApp(),
                environment.getDefaultMemoryOfApp(), environment.getStatus().getCode());

        environmentDAO.save(environmentDO);
    }
}
