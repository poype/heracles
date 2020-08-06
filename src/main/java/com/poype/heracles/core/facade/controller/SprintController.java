package com.poype.heracles.core.facade.controller;

import com.poype.heracles.core.facade.request.CreateNewSprintRequest;
import com.poype.heracles.core.facade.request.QuerySprintDetailRequest;
import com.poype.heracles.core.facade.result.CreateNewSprintResult;
import com.poype.heracles.core.facade.result.QuerySprintDetailResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/sprint")
public class SprintController {

    @RequestMapping(value = "/createNew", method = RequestMethod.POST, produces = "application/json")
    public CreateNewSprintResult createNew(CreateNewSprintRequest request) {



        return null;
    }

    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST, produces = "application/json")
    public QuerySprintDetailResult queryDetail(QuerySprintDetailRequest request) {
        return null;
    }

}
