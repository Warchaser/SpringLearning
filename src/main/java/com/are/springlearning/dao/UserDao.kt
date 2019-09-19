package com.are.springlearning.dao

import com.are.springlearning.bean.TUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Repository
interface UserDao : JpaRepository<TUserEntity, Int> {

    @Modifying
    @Transactional
    @Query("update TUserEntity us set us.age=:qAge, us.gender=:qGender where us.id=:qId")
    fun updateUser(
            @Param("qAge") age: Int?,
            @Param("qGender") gender: Int?,
            @Param("qId") id: Int?
    ) : Int

    /**
     * 查找字段为name的值为name的记录是否存在
     * */
    fun existsByName(name : String) : Boolean

    /**
     * 查找name和passwd均匹配的记录是否存在
     * */
    fun findByNameAndPasswd(name: String, passwd : String) : TUserEntity?
}
