package com.poype.heracles.core.domain.service.impl;

import com.poype.heracles.core.domain.model.Environment;
import com.poype.heracles.core.domain.model.enums.EnvironmentType;
import com.poype.heracles.core.domain.service.EnvironmentService;
import com.poype.heracles.core.repository.EnvironmentRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("environmentService")
public class EnvironmentServiceImpl implements EnvironmentService {

    @Resource
    private EnvironmentRepository environmentRepository;

    @Override
    public void addNewEnvironment(String envName, String envType, int defaultCpuOfApp, int defaultMemoryOfApp) {
        Environment environment = new Environment(envName, EnvironmentType.getByName(envType),
                defaultCpuOfApp, defaultMemoryOfApp);

        environmentRepository.saveEnvironment(environment);
    }

    @Override
    public List<Environment> queryAllEnvironment() {
        return environmentRepository.queryAll();
    }

    @Override
    public void updateEnvironment(int envId, int defaultCpuOfApp, int defaultMemoryOfApp) {
        Environment environment = environmentRepository.queryById(envId);
        environment.setDefaultCpuOfApp(defaultCpuOfApp);
        environment.setDefaultMemoryOfApp(defaultMemoryOfApp);

        environmentRepository.updateEnvironment(environment);
    }
}
