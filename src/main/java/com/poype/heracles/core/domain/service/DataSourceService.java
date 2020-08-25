package com.poype.heracles.core.domain.service;

import com.poype.heracles.core.domain.model.DataSource;

import java.util.List;

public interface DataSourceService {

    /**
     * 增加一个新的数据源
     */
    void addNewDataSource(String dataSourceName, String description, String dataSourceType, String ip,
                          String port, String userName, String password, String schema);

    List<DataSource> queryAll();
}
