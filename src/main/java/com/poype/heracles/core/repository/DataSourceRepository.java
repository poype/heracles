package com.poype.heracles.core.repository;

import com.poype.heracles.core.domain.model.DataSource;

import java.util.List;

public interface DataSourceRepository {

    void save(DataSource dataSource);

    DataSource queryByName(String dataSourceName);

    List<DataSource> queryAll();
}
