package com.are.springlearning.bean

import javax.persistence.*
import java.util.Objects

@Entity
@Table(name = "t_user", schema = "learning_spring_boot", catalog = "")
class TUserEntity {
    @get:Id
    @get:Column(name = "id", nullable = false)
    var id: Int = 0
    @get:Basic
    @get:Column(name = "name", nullable = false, length = 10)
    var name: String? = null
    @get:Basic
    @get:Column(name = "age", nullable = true)
    var age: Int? = null
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
        return Objects.hash(id, name, age, gender)
    }
}
