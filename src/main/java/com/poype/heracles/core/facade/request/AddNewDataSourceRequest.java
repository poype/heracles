package com.poype.heracles.core.facade.request;

public class AddNewDataSourceRequest {

    private String dataSourceName;

    private String description;

    private String dataSourceType;

    private String ip;

    private String port;

    private String userName;

    private String password;

    private String schema;

    @Override
    public String toString() {
        return "AddNewDataSourceRequest{" +
                "dataSourceName='" + dataSourceName + '\'' +
                ", description='" + description + '\'' +
                ", dataSourceType='" + dataSourceType + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", schema='" + schema + '\'' +
                '}';
    }

    public String getDataSourceType() {
        return dataSourceType;
    }

    public void setDataSourceType(String dataSourceType) {
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
