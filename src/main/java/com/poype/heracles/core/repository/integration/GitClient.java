package com.poype.heracles.core.repository.integration;

public interface GitClient {

    /**
     * clone代码库
     * @param gitUrl 代码库地址
     * @param appName 应用名字
     */
    void clone(String gitUrl, String appName);

    /**
     * 提交appName对应的配置文件
     * @param appName 应用名称
     */
    void commitAndPush(String appName);

    /**
     * 检查gitUrl是否有效
     * @param gitUrl gitUrl
     */
    void checkGitUrl(String gitUrl);

    /**
     * pull一个应用工程
     * @param appName 应用名称
     */
    void pull(String appName);

    /**
     * 创建一个新的分支
     * @param appName app应用名称
     * @param branchName 分支名称
     */
    void createNewBranch(String appName, String branchName);
}
