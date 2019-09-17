package com.are.springlearning.service

import com.are.springlearning.bean.GetUsersWithIndexResp
import com.are.springlearning.bean.ResponseBodyBean
import com.are.springlearning.bean.TUserEntity
import com.are.springlearning.dao.UserDao
import com.are.springlearning.util.TextUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.ArrayList

@Controller
@RequestMapping("/admin/user/")
class UserController @Autowired constructor(private val mUserDao: UserDao) {

    /**
     * 添加用户
     * */
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

    /**
     * 以Id删除用户
     * */
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

    /**
     * 以Id更新用户
     * */
    @ResponseBody
    @PostMapping(path = ["updateUserById"])
    fun updateUserById(@ModelAttribute("user") userEntity: TUserEntity) : ResponseBodyBean{
        if(TextUtil.isEmpty(userEntity.name)){
            return ResponseBodyBean(-1, "UserName can not be null or empty")
        }

        if(!mUserDao.existsById(userEntity.id)){
            return ResponseBodyBean(-1, "Item doesn't exist")
        }

        val affectedRows = mUserDao.updateUser(userEntity.name, userEntity.age, userEntity.gender, userEntity.id)
        mUserDao.flush()
        return if(affectedRows > 0){
            ResponseBodyBean()
        } else {
            ResponseBodyBean(-1, "Update user failed")
        }
    }

    /**
     * 分页查询
     * */
    @ResponseBody
    @GetMapping(path = ["getUsersByIndex"])
    fun getUsersWithIndex(pageNum : Int, pageSize : Int) : ResponseBodyBean{
        if(pageNum < 0 || pageSize <= 0){
            return ResponseBodyBean(-1, "Parameters are not valid")
        }

        val pageable = PageRequest.of(pageNum, pageSize)
        val pageSet : Page<TUserEntity> = mUserDao.findAll(pageable)

        val body = GetUsersWithIndexResp(pageNum, pageSize, pageSet.isLast, ArrayList(pageSet.content))

        return ResponseBodyBean(body)
    }

}
