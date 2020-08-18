package com.poype.heracles.core.repository.integration.impl;

import com.poype.heracles.common.constant.Constants;
import com.poype.heracles.common.enums.BusinessErrorCode;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.LogUtil;
import com.poype.heracles.core.repository.integration.GitClient;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.LsRemoteCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository("gitClient")
public class GitClientImpl implements GitClient, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(GitClient.class);

    private CredentialsProvider credentialsProvider;

    private String userName = "253186926@qq.com";

    private String password = "ldL643835@..0";

    @Override
    public void afterPropertiesSet() {
        this.credentialsProvider = new UsernamePasswordCredentialsProvider(userName, password);
    }

    @Override
    public void clone(String gitUrl, String appName) {
        try {
            Git.cloneRepository().
                setURI(gitUrl).
                setDirectory(new File(Constants.WORKSPACE_DIR + appName)).
                setCredentialsProvider(this.credentialsProvider).
                call();
        } catch (Exception e) {
            LogUtil.info(logger, "git clone error: " + e);
            AssertUtil.isTrue(false, BusinessErrorCode.GIT_CLONE_ERROR);
        }

    }

    @Override
    public void commitAndPush(String appName) {
        try {
            Git git = Git.open(new File(Constants.WORKSPACE_DIR + appName));
            git.add().addFilepattern(".").call();
            git.commit().setMessage("heracles platform commit").call();
            git.push().setCredentialsProvider(this.credentialsProvider).call();
        } catch (Exception ex) {
            LogUtil.info(logger, "git push error: " + ex);
            AssertUtil.isTrue(false, BusinessErrorCode.GIT_PUSH_ERROR);
        }
    }

    @Override
    public void checkGitUrl(String gitUrl) {
        LsRemoteCommand lsCmd = new LsRemoteCommand(null);
        lsCmd.setRemote(gitUrl);
        lsCmd.setCredentialsProvider(this.credentialsProvider);
//        try {
//            lsCmd.call();
//        } catch (GitAPIException e) {
//            LogUtil.info(logger, "check git url error: " + e);
//            AssertUtil.isTrue(false, BusinessErrorCode.GIT_ADDR_ILLEGAL);
//        }
    }

    @Override
    public void pull(String appName) {
        try {
            Git git = Git.open(new File(Constants.WORKSPACE_DIR + appName));
            git.pull().setCredentialsProvider(this.credentialsProvider).call();
        } catch (Exception ex) {
            LogUtil.info(logger, "git pull error: " + ex);
            AssertUtil.isTrue(false, BusinessErrorCode.GIT_PULL_ERROR);
        }
    }

    @Override
    public void createNewBranch(String appName, String branchName) {
        try {
            Git git = Git.open(new File(Constants.WORKSPACE_DIR + appName));
            git.checkout().setName("master").call();
            git.pull().setCredentialsProvider(this.credentialsProvider).call();
            RefSpec refSpec = new RefSpec().setSourceDestination("master", branchName);
            git.push().setRefSpecs(refSpec).setCredentialsProvider(this.credentialsProvider).call();
        } catch (Exception ex) {
            LogUtil.info(logger, "git create new branch error: " + ex);
            AssertUtil.isTrue(false, BusinessErrorCode.GIT_CREATE_BRANCH_ERROR);
        }
    }
}
