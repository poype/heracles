package com.poype.heracles.core.facade.controller;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.template.ExecuteCallback;
import com.poype.heracles.common.template.ExecuteTemplate;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import com.poype.heracles.core.domain.model.dto.AppOfSprintDto;
import com.poype.heracles.core.domain.model.dto.SimpleSprintDto;
import com.poype.heracles.core.domain.model.enums.AppOfSprintStatus;
import com.poype.heracles.core.domain.model.enums.SprintStatus;
import com.poype.heracles.core.domain.model.sprint.AppOfSprint;
import com.poype.heracles.core.domain.model.sprint.Sprint;
import com.poype.heracles.core.domain.service.SprintService;
import com.poype.heracles.core.facade.request.CreateNewSprintRequest;
import com.poype.heracles.core.facade.request.TransferAppStatusRequest;
import com.poype.heracles.core.facade.request.TransferSprintStatusRequest;
import com.poype.heracles.core.facade.request.UpdateSprintRequest;
import com.poype.heracles.core.facade.result.*;
import com.poype.heracles.core.manager.SprintManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @ResponseBody
    public CreateNewSprintResult createNew(@RequestBody final CreateNewSprintRequest request) {
        ThreadLocalHolder.setBizScene(BizScene.CREATE_NEW_SPRINT);

        final CreateNewSprintResult result = new CreateNewSprintResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getName(), PARAM_ILLEGAL, "版本名称不能为空");
                AssertUtil.notBlank(request.getDescription(), PARAM_ILLEGAL, "版本描述不能为空");
                AssertUtil.notBlank(request.getReleaseDate(), PARAM_ILLEGAL, "版本日期不能为空");
                AssertUtil.notEmpty(request.getAppList(), PARAM_ILLEGAL, "版本应用不能为空");
                for (AppOfSprintDto app : request.getAppList()) {
                    AssertUtil.notBlank(app.getAppName(), PARAM_ILLEGAL, "应用名字不能为空");
                    AssertUtil.notEmpty(app.getDevList(), PARAM_ILLEGAL, "必须制定至少一个研发人员");
                    AssertUtil.notEmpty(app.getQaList(), PARAM_ILLEGAL, "必须制定至少一个测试人员");
                }
            }

            @Override
            public void doService() {
                String createUser = "poype";
                String sprintId = sprintManager.createNewSprint(request.getName(), request.getDescription(),
                        request.getReleaseDate(), request.getAppList(), createUser);
                result.setSprintId(sprintId);
            }
        });
        return result;
    }

    /**
     * 变更应用信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public BaseResult update(@RequestBody final UpdateSprintRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.UPDATE_SPRINT);

        final BaseResult result = new BaseResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getSprintId(), PARAM_ILLEGAL, "版本Id不能为空");
                for (AppOfSprintDto app : request.getAppList()) {
                    AssertUtil.notBlank(app.getAppName(), PARAM_ILLEGAL, "应用名字不能为空");
                    AssertUtil.notEmpty(app.getDevList(), PARAM_ILLEGAL, "必须制定至少一个研发人员");
                    AssertUtil.notEmpty(app.getQaList(), PARAM_ILLEGAL, "必须制定至少一个测试人员");
                }
            }

            @Override
            public void doService() {
                String createUser = "poype";
                sprintManager.update(request.getSprintId(), request.getAppList());
            }
        });
        return result;
    }

    @RequestMapping(value = "/queryDetail", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QuerySprintDetailResult queryDetail(@RequestParam("sprintId") final String sprintId) {
        ThreadLocalHolder.setBizScene(BizScene.QUERY_SPRINT_DETAIL);

        final QuerySprintDetailResult result = new QuerySprintDetailResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(sprintId, PARAM_ILLEGAL);
            }

            @Override
            public void doService() {
                Sprint sprint = sprintManager.queryBySprintId(sprintId);

                SprintView sprintView = new SprintView();
                sprintView.setSprintId(sprint.getSprintId());
                sprintView.setName(sprint.getSprintName());
                sprintView.setDescription(sprint.getDescription());
                sprintView.setReleaseDate(sprint.getReleaseDate());
                sprintView.setStatus(sprint.getStatus().getName());
                sprintView.setTestEnv(sprint.getSitEnvName());

                List<AppOfSprintView> appList = new ArrayList<>();
                for (AppOfSprint appOfSprint : sprint.getApplications()) {
                    AppOfSprintView appOfSprintView = new AppOfSprintView();
                    appOfSprintView.setAppName(appOfSprint.getApp());
                    appOfSprintView.setCodeBranch(appOfSprint.getCodeBranch());
                    appOfSprintView.setCodeRepos(appOfSprint.getCodeRepository());
                    appOfSprintView.setAppType(appOfSprint.getAppType().getName());
                    appOfSprintView.setStatus(appOfSprint.getStatus().getName());
                    appOfSprintView.setDevList(appOfSprint.getDevList());
                    appOfSprintView.setQaList(appOfSprint.getQaList());
                    appList.add(appOfSprintView);
                }
                sprintView.setAppList(appList);
                result.setSprintInfo(sprintView);
            }
        });
        return result;
    }

    @RequestMapping(value = "/querySimpleList", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QuerySprintSimpleListResult querySimpleList(@RequestParam("pageNum") final int pageNum) {

        ThreadLocalHolder.setBizScene(BizScene.SPRINT_QUERY_SIMPLE_LIST);

        final QuerySprintSimpleListResult result = new QuerySprintSimpleListResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.isTrue(pageNum > 0, PARAM_ILLEGAL);
            }

            @Override
            public void doService() {
                Map<String, Object> map = sprintManager.querySimpleSprintList(pageNum);
                result.setSprintList((List<SimpleSprintDto>) map.get("sprintList"));
                result.setTotal((int) map.get("total"));
            }
        });
        return result;

    }

    @RequestMapping(value = "/createCodeBranch", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public BaseResult createCodeBranch(@RequestParam("sprintId") final String sprintId) {
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

    @RequestMapping(value = "/transferAppStatus", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public TransferAppStatusResult transferAppStatus(@RequestBody final TransferAppStatusRequest request) {
        ThreadLocalHolder.setBizScene(BizScene.TRANSFER_APP_OF_SPRINT_STATUS);

        final TransferAppStatusResult result = new TransferAppStatusResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getSprintId(), PARAM_ILLEGAL, "sprintId can't be blank");
                AssertUtil.notBlank(request.getApp(), PARAM_ILLEGAL, "app name can't be blank");
                AssertUtil.notBlank(request.getStatus(), PARAM_ILLEGAL, "status can't be blank");

                AppOfSprintStatus targetStatus = AppOfSprintStatus.getByName(request.getStatus());
                AssertUtil.isTrue(targetStatus == AppOfSprintStatus.SIT
                        || targetStatus == AppOfSprintStatus.UAT
                        || targetStatus == AppOfSprintStatus.FINISH,
                        PARAM_ILLEGAL, "status只能是SIT、UAT、FINISH");
            }

            @Override
            public void doService() {
                String sprintStatus = sprintService.transferAppOfSprintStatus(request.getSprintId(),
                        request.getApp(), request.getStatus());
                result.setSprintStatus(sprintStatus);
            }
        });

        return result;
    }

    @RequestMapping(value = "/transferSprintStatus", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public BaseResult transferSprintStatus(@RequestBody final TransferSprintStatusRequest request) {
        ThreadLocalHolder.setBizScene(BizScene.TRANSFER_WHOLE_SPRINT_STATUS);

        final BaseResult result = new BaseResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getSprintId(), PARAM_ILLEGAL, "sprintId can't be blank");
                AssertUtil.notBlank(request.getStatus(), PARAM_ILLEGAL, "status can't be blank");

                SprintStatus targetStatus = SprintStatus.getByName(request.getStatus());
                AssertUtil.isTrue(targetStatus == SprintStatus.FINISH_RC_TEST
                                || targetStatus == SprintStatus.FINISH_PROD_VERIFY,
                                PARAM_ILLEGAL, "status只能是FINISH_RC_TEST、FINISH_PROD_VERIFY");
            }

            @Override
            public void doService() {
                sprintService.transferWholeSprintStatus(request.getSprintId(), request.getStatus());
            }
        });

        return result;
    }
}
