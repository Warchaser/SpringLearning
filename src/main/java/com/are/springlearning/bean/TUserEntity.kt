package com.are.springlearning.bean

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import javax.persistence.*
import java.util.Objects

@Entity
@ApiModel(value = "用户")
@Table(name = "t_user", schema = "learning_spring_boot", catalog = "")
open class TUserEntity {

    @ApiModelProperty(hidden = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @get:Id
    @get:Column(name = "id", nullable = false)
    var id: Int = 0

    @ApiModelProperty(value = "用户名", required = true)
    @get:Basic
    @get:Column(name = "name", nullable = false, length = 10)
    var name: String = ""

    @ApiModelProperty(value = "密码", required = true)
    @get:Basic
    @get:Column(name = "passwd", nullable = false, length = 32)
    var passwd: String = ""

    @ApiModelProperty(value = "年龄")
    @get:Basic
    @get:Column(name = "age", nullable = true)
    var age: Int? = null

    @ApiModelProperty(value = "性别 0男 1 女")
    @get:Basic
    @get:Column(name = "gender", nullable = true)
    var gender: Int? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val that = o as TUserEntity?
        return id == that!!.id &&
                name == that.name &&
                age == that.age &&
                gender == that.gender
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name, age, gender, passwd)
    }
}
