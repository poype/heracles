package com.poype.heracles.core.repository.impl;

import com.poype.heracles.core.domain.model.Environment;
import com.poype.heracles.core.repository.EnvironmentRepository;
import org.springframework.stereotype.Repository;

@Repository("environmentRepository")
public class EnvironmentRepositoryImpl implements EnvironmentRepository {

    @Override
    public Environment queryByEnvName(String name) {
        Environment environment = new Environment("test", 4, 8);
        return environment;
    }
}
