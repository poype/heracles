package com.poype.heracles.core.repository.dao;

import com.poype.heracles.core.repository.dao.model.DataSourceDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DataSourceDAO {

    void save(DataSourceDO dataSourceDO);

    DataSourceDO queryByName(@Param("dataSourceName") String dataSourceName);

    List<DataSourceDO> queryAll();
}
