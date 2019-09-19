package com.are.springlearning.service

import com.are.springlearning.bean.GetUsersWithIndexResp
import com.are.springlearning.bean.ResponseBodyBean
import com.are.springlearning.bean.TUserEntity
import com.are.springlearning.bean.UpdateUserInfoReq
import com.are.springlearning.dao.UserDao
import com.are.springlearning.util.TextUtil
import io.swagger.annotations.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.ArrayList

@Api(value = "管理员接口")
@Controller
@RequestMapping("/admin/user/")
class UserController @Autowired constructor(private val mUserDao: UserDao) {

    /**
     * 添加用户
     * */
    @ApiOperation(value = "管理员新增用户", notes = "用户名必填")
    @ResponseBody
    @PostMapping(path = ["add"])
    fun addUserPost(@ModelAttribute("user") userEntity: TUserEntity): ResponseBodyBean {
        val response : ResponseBodyBean
        response = when {
            TextUtil.isEmpty(userEntity.name) -> {
                ResponseBodyBean(-1, "UserName can not be null or empty")
            }
            mUserDao.existsByName(userEntity.name) -> {
                ResponseBodyBean(-1, "User exists already")
            }
            userEntity.name.length > 10 -> {
                ResponseBodyBean(-1, "UserName's max length is 10")
            }
            else -> {
                userEntity.passwd = userEntity.passwd.trim()

                if (TextUtil.isEmpty(userEntity.passwd)) {
                    ResponseBodyBean()
                } else if (userEntity.passwd.length > 32) {
                    ResponseBodyBean(-1, "Password's max length is 32")
                }

                val result = mUserDao.saveAndFlush(userEntity)
                return if (result?.name != null) {
                    ResponseBodyBean()
                } else {
                    ResponseBodyBean(-1, "insert failed")
                }
            }
        }

        return response
    }

    /**
     * 以Id删除用户
     * */
    @ApiOperation(value = "管理员删除用户", notes = "Id必填")
    @ApiImplicitParam(name = "id", required = true)
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
    @ApiOperation(value = "管理员更新用户")
    @ResponseBody
    @PostMapping(path = ["updateUserById"])
    fun updateUserById(@ModelAttribute("user") userEntity: UpdateUserInfoReq) : ResponseBodyBean{
        if(!mUserDao.existsById(userEntity.id)){
            return ResponseBodyBean(-1, "Item doesn't exist")
        }

        val affectedRows = mUserDao.updateUser(userEntity.age, userEntity.gender, userEntity.id)
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
    @ApiOperation(value = "管理员分页查询用户", notes = "pageNum以0开始, pageSize须大于0")
    @ResponseBody
    @GetMapping(path = ["getUsersByIndex"])
    @ApiImplicitParams(
        ApiImplicitParam(name = "pageNum", value = "页码，0为第一页", type = "int", required = true),
        ApiImplicitParam(name = "pageSize", value = "步长", type = "int", required = true)
    )
    fun getUsersWithIndex(pageNum : Int, pageSize : Int) : ResponseBodyBean{
        if(pageNum < 0 || pageSize <= 0){
            return ResponseBodyBean(-1, "Parameters are not valid")
        }

        val pageable = PageRequest.of(pageNum, pageSize)
        val pageSet : Page<TUserEntity> = mUserDao.findAll(pageable)

        val body = GetUsersWithIndexResp(pageNum, pageSize, pageSet.isLast, ArrayList(pageSet.content))

        return ResponseBodyBean(body)
    }

    /**
     * 用户登陆
     * */
    @ApiOperation(value = "用户登陆")
    @ResponseBody
    @GetMapping(path = ["userLogin"])
    @ApiImplicitParams(
            ApiImplicitParam(name = "userName", value = "用户名", type = "String", required = true),
            ApiImplicitParam(name = "passwd", value = "密码", type = "String", required = true)
    )
    fun login(userName : String, passwd : String) : ResponseBodyBean{

        if(TextUtil.isEmpty(userName) || TextUtil.isEmpty(passwd)){
            return ResponseBodyBean(-1, "UserName or Password is empty")
        }

        val result : TUserEntity? = mUserDao.findByNameAndPasswd(userName, passwd)

        return if(result == null){
            ResponseBodyBean(-1, "UserName or Password is not correct")
        } else {
            ResponseBodyBean()
        }
    }

}
