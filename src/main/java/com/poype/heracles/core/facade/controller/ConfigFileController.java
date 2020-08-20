package com.poype.heracles.core.facade.controller;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.template.ExecuteCallback;
import com.poype.heracles.common.template.ExecuteTemplate;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import com.poype.heracles.core.facade.result.QueryAllHostConfigNameResult;
import com.poype.heracles.core.manager.ConfigFileManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;

import static com.poype.heracles.common.enums.BusinessErrorCode.PARAM_ILLEGAL;

@Controller
@RequestMapping(value="/config")
public class ConfigFileController {

    @Resource
    private ExecuteTemplate executeTemplate;

    @Resource
    private ConfigFileManager configFileManager;

    @RequestMapping(value = "/generate", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public BaseResult generateConfigFileForApp(@RequestParam("appId") final String appId) {

        ThreadLocalHolder.setBizScene(BizScene.GENERATE_CONFIG_FILE_FOR_APP);

        final BaseResult result = new BaseResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(appId, PARAM_ILLEGAL, "appId can't be blank");
            }

            @Override
            public void doService() {
                configFileManager.generateConfigFileForApp(appId);
            }
        });

        return result;
    }

    @RequestMapping(value = "/queryAllHostName", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QueryAllHostConfigNameResult queryAllHostName() {
        ThreadLocalHolder.setBizScene(BizScene.QUERY_ALL_HOST);

        final QueryAllHostConfigNameResult result = new QueryAllHostConfigNameResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void doService() {
                List<String> hostNames = configFileManager.queryAllHostNames();
                result.setHostNameList(hostNames);
            }
        });

        return result;
    }
}
