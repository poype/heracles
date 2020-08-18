package com.poype.heracles.core.facade.controller;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.template.ExecuteCallback;
import com.poype.heracles.common.template.ExecuteTemplate;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import com.poype.heracles.core.domain.model.dto.AppOfSprintDto;
import com.poype.heracles.core.domain.model.sprint.AppOfSprint;
import com.poype.heracles.core.domain.model.sprint.Sprint;
import com.poype.heracles.core.domain.service.SprintService;
import com.poype.heracles.core.facade.request.CreateNewSprintRequest;
import com.poype.heracles.core.facade.request.QuerySprintDetailRequest;
import com.poype.heracles.core.facade.result.AppOfSprintView;
import com.poype.heracles.core.facade.result.CreateNewSprintResult;
import com.poype.heracles.core.facade.result.QuerySprintDetailResult;
import com.poype.heracles.core.manager.SprintManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static com.poype.heracles.common.enums.BusinessErrorCode.PARAM_ILLEGAL;

@Controller
@RequestMapping(value="/sprint")
public class SprintController {

    @Resource
    private ExecuteTemplate executeTemplate;

    @Resource
    private SprintManager sprintManager;

    @Resource
    private SprintService sprintService;

    @RequestMapping(value = "/createNew", method = RequestMethod.POST, produces = "application/json")
    public CreateNewSprintResult createNew(final CreateNewSprintRequest request) {
        ThreadLocalHolder.setBizScene(BizScene.CREATE_NEW_SPRINT);

        final CreateNewSprintResult result = new CreateNewSprintResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getName(), PARAM_ILLEGAL, "版本名称不能为空");
                AssertUtil.notBlank(request.getDescription(), PARAM_ILLEGAL, "版本描述不能为空");
                AssertUtil.notBlank(request.getReleaseDate(), PARAM_ILLEGAL, "版本日期不能为空");
                AssertUtil.notEmpty(request.getApps(), PARAM_ILLEGAL, "版本应用不能为空");
                for (AppOfSprintDto app : request.getApps()) {
                    AssertUtil.notBlank(app.getAppName(), PARAM_ILLEGAL, "应用名字不能为空");
                    AssertUtil.notEmpty(app.getDevNames(), PARAM_ILLEGAL, "必须制定至少一个研发人员");
                    AssertUtil.notEmpty(app.getQaNames(), PARAM_ILLEGAL, "必须制定至少一个测试人员");
                }
            }

            @Override
            public void doService() {
                String createUser = "poype";
                String sprintId = sprintManager.createNewSprint(request.getName(), request.getDescription(),
                        request.getReleaseDate(), request.getApps(), createUser);
                result.setSprintId(sprintId);
            }
        });
        return result;
    }

    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST, produces = "application/json")
    public QuerySprintDetailResult queryDetail(final QuerySprintDetailRequest request) {
        ThreadLocalHolder.setBizScene(BizScene.QUERY_SPRINT_DETAIL);

        final QuerySprintDetailResult result = new QuerySprintDetailResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getSprintId(), PARAM_ILLEGAL);
            }

            @Override
            public void doService() {
                Sprint sprint = sprintManager.queryBySprintId(request.getSprintId());
                result.setSprintId(sprint.getSprintId());
                result.setName(sprint.getSprintName());
                result.setDescription(sprint.getDescription());
                result.setReleaseDate(sprint.getReleaseDate());
                result.setStatus(sprint.getStatus().getName());
                result.setTestEnv(sprint.getSitEnvName());

                List<AppOfSprintView> appList = new ArrayList<>();
                for (AppOfSprint appOfSprint : sprint.getApplications()) {
                    AppOfSprintView appOfSprintView = new AppOfSprintView();
                    appOfSprintView.setAppName(appOfSprint.getApp());
                    appOfSprintView.setCodeBranch(appOfSprint.getCodeBranch());
                    appOfSprintView.setCodeRepos(appOfSprint.getCodeRepository());
                    appOfSprintView.setAppType(appOfSprint.getAppType().getName());
                    appList.add(appOfSprintView);
                }
                result.setAppList(appList);
            }
        });
        return result;
    }

    @RequestMapping(value = "/createCodeBranch", method = RequestMethod.GET, produces = "application/json")
    public BaseResult createCodeBranch(final String sprintId) {
        ThreadLocalHolder.setBizScene(BizScene.CREATE_NEW_BRANCH);

        final BaseResult result = new BaseResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(sprintId, PARAM_ILLEGAL);
            }

            @Override
            public void doService() {
                sprintService.createCodeBranch(sprintId);
            }
        });
        return result;
    }
}
