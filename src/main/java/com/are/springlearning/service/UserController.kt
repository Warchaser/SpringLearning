package com.are.springlearning.service

import com.are.springlearning.bean.ResponseBodyBean
import com.are.springlearning.bean.TUserEntity
import com.are.springlearning.dao.UserDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/admin/user/")
class UserController @Autowired constructor(private val mUserDao: UserDao) {

    @ResponseBody
    @PostMapping(path = ["add"])
    fun addUserPost(@ModelAttribute("user") userEntity: TUserEntity): ResponseBodyBean {
        println("addUserPost")
        val result = mUserDao.saveAndFlush(userEntity)
        return if (result?.name != null) {
            ResponseBodyBean()
        } else {
            ResponseBodyBean(-1, "insert failed")
        }
    }

    @ResponseBody
    @GetMapping(path = ["deleteById"])
    fun deleteUserById(id : Int) : ResponseBodyBean{
        return if(mUserDao.existsById(id)){
            mUserDao.deleteById(id)
            mUserDao.flush()
            ResponseBodyBean()
        } else {
            ResponseBodyBean(-1, "Item doesn't exist")
        }
    }

}
