package com.pandora.lease.controller.user;

import com.pandora.lease.model.common.ResultModel;
import com.pandora.lease.service.user.UserService;
import com.pandora.lease.model.manager.user.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manager")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    /**
     * 用户服务
     */
    @Autowired
    private UserService userService;
    /**
     * 注册用户
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public ResultModel register(UserModel userModel){
        userModel.setActivate_phone(false);
        int result=userService.registerUser(userModel);
        logger.info("注册用户："+userModel+"\n"+result);
        ResultModel resultModel;
        if(result>0){
            resultModel= new ResultModel(true,"注册成功!",null);
        }else{
            resultModel=new ResultModel(false,"注册失败!",null);
        }
        return resultModel;
    }

    /**
     * 修改用户信息
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateUser")
    public ResultModel updateUser(UserModel userModel){
        int result=userService.updateUser(userModel);
        logger.info("修改用户："+userModel+"\n"+result);
        ResultModel resultModel;
        if(result>0){
            resultModel= new ResultModel(true,"修改成功!",null);
        }else{
            resultModel=new ResultModel(false,"修改失败!",null);
        }
        return resultModel;
    }
}
