package com.are.springlearning.bean

import com.fasterxml.jackson.annotation.JsonProperty

class GetUsersWithIndexResp {

    @JsonProperty
    private var pageNum : Int = 0

    @JsonProperty
    private var pageSize : Int = 0

    @JsonProperty
    private var isLast : Boolean = false

    @JsonProperty
    private var array : ArrayList<TUserEntity> = ArrayList()
        set(value){
            field.addAll(value)
        }

    constructor(pageNum : Int, pageSize : Int, isLast : Boolean, data : ArrayList<TUserEntity>){
        this.pageNum = pageNum
        this.pageSize = pageSize
        this.isLast = isLast
        this.array.addAll(data)
    }

}


