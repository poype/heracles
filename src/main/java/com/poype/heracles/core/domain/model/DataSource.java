package com.poype.heracles.core.domain.model;

import com.poype.heracles.common.util.IdUtil;
import com.poype.heracles.core.domain.model.enums.DataSourceType;

public class DataSource {

    private String dsId;

    private String dataSourceName;

    private String description;

    private DataSourceType dataSourceType;

    private String ip;

    private String port;

    private String userName;

    private String password;

    private String schema;

    public DataSource(String dataSourceName, String description, String dataSourceType, String ip,
                      String port, String userName, String password, String schema) {
        this.dsId = IdUtil.generateBizId();
        this.dataSourceName = dataSourceName;
        this.description = description;
        this.dataSourceType = DataSourceType.getByName(dataSourceType);
        this.ip = ip;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.schema = schema;
    }

    public DataSource(String dsId, String dataSourceName, String description, DataSourceType dataSourceType,
                      String ip, String port, String userName, String password, String schema) {
        this.dsId = dsId;
        this.dataSourceName = dataSourceName;
        this.description = description;
        this.dataSourceType = dataSourceType;
        this.ip = ip;
        this.port = port;
        this.userName = userName;
        this.password = password;
        this.schema = schema;
    }

    public String getDsId() {
        return dsId;
    }

    public void setDsId(String dsId) {
        this.dsId = dsId;
    }

    public DataSourceType getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(DataSourceType dataSourceType) {
        this.dataSourceType = dataSourceType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}