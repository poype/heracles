package com.poype.heracles.core.facade.result;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.core.domain.model.DataSource;

import java.util.List;

public class QueryDataSourceResult extends BaseResult {

    private List<DataSource> dataSourceList;

    @Override
    public String toString() {
        return "QueryDataSourceResult{" +
                "dataSourceList=" + dataSourceList +
                ", " + super.toString() +
                '}';
    }

    public List<DataSource> getDataSourceList() {
        return dataSourceList;
    }

    public void setDataSourceList(List<DataSource> dataSourceList) {
        this.dataSourceList = dataSourceList;
    }
}
