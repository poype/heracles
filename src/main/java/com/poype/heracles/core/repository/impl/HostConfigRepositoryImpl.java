package com.poype.heracles.core.repository.impl;

import com.poype.heracles.core.domain.model.HostConfig;
import com.poype.heracles.core.repository.HostConfigRepository;
import com.poype.heracles.core.repository.dao.HostConfigDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("hostConfigRepository")
public class HostConfigRepositoryImpl implements HostConfigRepository {

    @Resource
    private HostConfigDAO hostConfigDAO;

    @Override
    public HostConfig queryByConfigName(String configName) {
        return null;
    }

    @Override
    public List<String> queryAllHostNames() {
        return hostConfigDAO.queryAllHostNames();
    }
}
