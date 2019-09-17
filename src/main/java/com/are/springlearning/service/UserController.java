package com.are.springlearning.service;

import com.are.springlearning.bean.ResponseBodyBean;
import com.are.springlearning.bean.TUserEntity;
import com.are.springlearning.dao.UserDao;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserDao mUserDao;

    @Autowired
    public UserController(UserDao userDao){
        mUserDao = userDao;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
    public ResponseBodyBean addUserPost(@ModelAttribute("user") TUserEntity userEntity){
        System.out.println("addUserPost");
        TUserEntity result = mUserDao.saveAndFlush(userEntity);
        if(result != null && result.getName() != null){
            return new ResponseBodyBean();
        }
        return new ResponseBodyBean(-1, "insert failed", "{}");
    }

}
