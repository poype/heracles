package com.poype.heracles.core.facade.controller;

import com.poype.heracles.common.dto.BaseResult;
import com.poype.heracles.common.dto.error.BizScene;
import com.poype.heracles.common.template.ExecuteCallback;
import com.poype.heracles.common.template.ExecuteTemplate;
import com.poype.heracles.common.util.AssertUtil;
import com.poype.heracles.common.util.ThreadLocalHolder;
import com.poype.heracles.core.domain.model.DataSource;
import com.poype.heracles.core.domain.service.DataSourceService;
import com.poype.heracles.core.facade.request.AddNewDataSourceRequest;
import com.poype.heracles.core.facade.result.QueryDataSourceResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;

import static com.poype.heracles.common.enums.BusinessErrorCode.PARAM_ILLEGAL;

@Controller
@RequestMapping(value="/data")
public class DataSourceController {

    @Resource
    private DataSourceService dataSourceService;

    @Resource
    private ExecuteTemplate executeTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public BaseResult addNewDataSource(@RequestBody final AddNewDataSourceRequest request) {

        ThreadLocalHolder.setBizScene(BizScene.DATA_SOURCE);

        final BaseResult result = new BaseResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void check() {
                AssertUtil.notBlank(request.getDataSourceName(), PARAM_ILLEGAL,
                        "dataSourceName can't be blank");
                AssertUtil.notBlank(request.getDescription(), PARAM_ILLEGAL,
                        "description can't be blank");
                AssertUtil.notBlank(request.getDataSourceType(), PARAM_ILLEGAL,
                        "dataSourceType can't be blank");
                AssertUtil.notBlank(request.getIp(), PARAM_ILLEGAL,
                        "ip address can't be blank");
                AssertUtil.notBlank(request.getPort(), PARAM_ILLEGAL,
                        "port can't be blank");
                AssertUtil.notBlank(request.getUserName(), PARAM_ILLEGAL,
                        "userName can't be blank");
                AssertUtil.notBlank(request.getPassword(), PARAM_ILLEGAL,
                        "password can't be blank");
                AssertUtil.notBlank(request.getSchema(), PARAM_ILLEGAL,
                        "schema can't be blank");
            }

            @Override
            public void doService() {
                dataSourceService.addNewDataSource(request.getDataSourceName(), request.getDescription(),
                        request.getDataSourceType(), request.getIp(), request.getPort(),
                        request.getUserName(), request.getPassword(), request.getSchema());
            }
        });

        return result;
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QueryDataSourceResult queryAllDataSource() {

        ThreadLocalHolder.setBizScene(BizScene.DATA_SOURCE);

        final QueryDataSourceResult result = new QueryDataSourceResult();

        executeTemplate.execute(result, new ExecuteCallback() {

            @Override
            public void doService() {
                List<DataSource> dataSourceList = dataSourceService.queryAll();
                result.setDataSourceList(dataSourceList);
            }
        });

        return result;
    }
}
