package com.poype.heracles.core.repository.impl;

import com.poype.heracles.core.domain.model.DataSource;
import com.poype.heracles.core.domain.model.enums.DataSourceType;
import com.poype.heracles.core.repository.DataSourceRepository;
import com.poype.heracles.core.repository.dao.DataSourceDAO;
import com.poype.heracles.core.repository.dao.model.DataSourceDO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Repository("dataSourceRepository")
public class DataSourceRepositoryImpl implements DataSourceRepository {

    @Resource
    private DataSourceDAO dataSourceDAO;

    @Override
    public void save(DataSource dataSource) {
        DataSourceDO dataSourceDO = new DataSourceDO();
        dataSourceDO.setDsId(dataSource.getDsId());
        dataSourceDO.setDataSourceName(dataSource.getDataSourceName());
        dataSourceDO.setDescription(dataSource.getDescription());
        dataSourceDO.setDataSourceType(dataSource.getDataSourceType().getCode());
        dataSourceDO.setIp(dataSource.getIp());
        dataSourceDO.setPort(dataSource.getPort());
        dataSourceDO.setUserName(dataSource.getUserName());
        dataSourceDO.setPassword(dataSource.getPassword());
        dataSourceDO.setSchema(dataSource.getSchema());

        System.out.println(dataSourceDO);
        dataSourceDAO.save(dataSourceDO);
    }

    @Override
    public DataSource queryByName(String dataSourceName) {
        DataSourceDO dataSourceDO = dataSourceDAO.queryByName(dataSourceName);
        return new DataSource(dataSourceDO.getDsId(), dataSourceDO.getDataSourceName(),
                dataSourceDO.getDescription(), DataSourceType.getByCode(dataSourceDO.getDataSourceType()),
                dataSourceDO.getIp(), dataSourceDO.getPort(), dataSourceDO.getUserName(),
                dataSourceDO.getPassword(), dataSourceDO.getSchema());
    }

    @Override
    public List<DataSource> queryAll() {
        List<DataSourceDO> dataSourceDOList = dataSourceDAO.queryAll();
        List<DataSource> dataSourceList = new ArrayList<>();

        for (DataSourceDO dataSourceDO : dataSourceDOList) {
            DataSource dataSource = new DataSource(dataSourceDO.getDsId(), dataSourceDO.getDataSourceName(),
                    dataSourceDO.getDescription(), DataSourceType.getByCode(dataSourceDO.getDataSourceType()),
                    dataSourceDO.getIp(), dataSourceDO.getPort(), dataSourceDO.getUserName(),
                    dataSourceDO.getPassword(), dataSourceDO.getSchema());
            dataSourceList.add(dataSource);
        }
        return dataSourceList;
    }
}
