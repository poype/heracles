package com.poype.heracles.core.repository.impl;

import com.poype.heracles.core.domain.model.application.Application;
import com.poype.heracles.core.repository.ApplicationRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("applicationRepository")
public class ApplicationRepositoryImpl implements ApplicationRepository {

    @Override
    public void addApplicationBasicInfo(Application application) {

    }

    @Override
    public Application queryByAppName(String appName) {
        return null;
    }


}
