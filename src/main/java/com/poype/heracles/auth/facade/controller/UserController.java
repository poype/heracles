package com.poype.heracles.auth.facade.controller;

import com.poype.heracles.auth.facade.controller.result.QueryAllUserNameResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class UserController {

    @RequestMapping(value = "/queryAllUserName", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public QueryAllUserNameResult addApplication() {

        List<String> userNames = new ArrayList<>();
        userNames.add("liudongliang214");
        userNames.add("chensheng232");
        userNames.add("luoweichao112");
        userNames.add("zhangjianwei332");
        userNames.add("zhangdan223");
        userNames.add("lisi443");
        userNames.add("wangdawei432");
        userNames.add("zhangdanl86");
        userNames.add("TestABC889");

        QueryAllUserNameResult result = new QueryAllUserNameResult();
        result.setUserNameList(userNames);
        return result;
    }

}
