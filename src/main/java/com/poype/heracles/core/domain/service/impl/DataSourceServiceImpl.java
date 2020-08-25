package com.poype.heracles.core.domain.service.impl;

import com.poype.heracles.core.domain.model.DataSource;
import com.poype.heracles.core.domain.service.DataSourceService;
import com.poype.heracles.core.repository.DataSourceRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dataSourceService")
public class DataSourceServiceImpl implements DataSourceService {

    @Resource
    private DataSourceRepository dataSourceRepository;

    @Override
    public void addNewDataSource(String dataSourceName, String description, String dataSourceType, String ip,
                                 String port, String userName, String password, String schema) {

        DataSource dataSource = new DataSource(dataSourceName, description, dataSourceType, ip, port,
                userName, password, schema);
        dataSourceRepository.save(dataSource);
    }

    @Override
    public List<DataSource> queryAll() {
        return dataSourceRepository.queryAll();
    }
}
