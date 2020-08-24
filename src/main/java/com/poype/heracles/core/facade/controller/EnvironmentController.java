package com.poype.heracles.core.facade.controller;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.template.ExecuteCallback;
import com.poype.heracles.common.template.ExecuteTemplate;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import com.poype.heracles.core.domain.model.enums.EnvironmentType;
import com.poype.heracles.core.domain.service.EnvironmentService;
import com.poype.heracles.core.facade.request.AddNewSitEnvRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import static com.poype.heracles.common.enums.BusinessErrorCode.PARAM_ILLEGAL;

@Controller
@RequestMapping(value="/environment")
public class EnvironmentController {

    @Resource
    private ExecuteTemplate executeTemplate;

    @Resource
    private EnvironmentService environmentService;

    @RequestMapping(value = "/addNewEnv", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public BaseResult addNewEnv(@RequestBody final AddNewSitEnvRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.ADD_APPLICATION);

        BaseResult result = new BaseResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getEnvName(), PARAM_ILLEGAL);
                AssertUtil.notBlank(request.getEnvType(), PARAM_ILLEGAL);
                AssertUtil.isTrue(request.getDefaultCpuOfApp() > 0, PARAM_ILLEGAL);
                AssertUtil.isTrue(request.getDefaultMemoryOfApp() > 0, PARAM_ILLEGAL);
                AssertUtil.notNull(EnvironmentType.getByName(request.getEnvType()), PARAM_ILLEGAL);
            }

            @Override
            public void doService() {
                environmentService.addNewEnvironment(request.getEnvName(), request.getEnvType(),
                        request.getDefaultCpuOfApp(), request.getDefaultMemoryOfApp());
            }
        });

        return result;
    }
}
