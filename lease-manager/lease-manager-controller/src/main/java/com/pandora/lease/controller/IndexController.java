package com.pandora.lease.controller;


import com.pandora.lease.model.common.ResultModel;
import com.pandora.lease.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    /**
     * 用户服务
     */
    @Autowired
    private UserService userService;

    /**
     * 首页
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/index")
    public ResultModel index() {
        //int i = userService.getMaxVersionByCode("1");
        //System.out.println(i);
        ResultModel result = new ResultModel();
        result.setMsg("修改失败");
        result.setStatus(-1);
        result.setSuccess(false);
        return result;
    }

    @RequestMapping(value = "/index1", method = RequestMethod.GET)
    public ModelAndView index1() {
        return new ModelAndView("/manager/registuser");
    }
}
