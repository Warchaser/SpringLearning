package com.are.springlearning.service

import com.are.springlearning.bean.ResponseBodyBean
import com.are.springlearning.bean.TUserEntity
import com.are.springlearning.dao.UserDao
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class UserController @Autowired constructor(private val mUserDao: UserDao) {

    @ResponseBody
    @RequestMapping(value = ["/admin/user/add"], method = [RequestMethod.POST])
    fun addUserPost(@ModelAttribute("user") userEntity: TUserEntity): ResponseBodyBean {
        println("addUserPost")
        val result = mUserDao.saveAndFlush(userEntity)

        return if (result?.name != null) {
            ResponseBodyBean()
        } else {
            ResponseBodyBean(-1, "insert failed", "{}")
        }
    }

}
