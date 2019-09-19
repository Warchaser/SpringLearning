package com.are.springlearning.bean;

import io.swagger.annotations.ApiModelProperty;

public class UpdateUserInfoReq {

    @ApiModelProperty(value = "id", dataType = "int", required = true)
    private int id;

    @ApiModelProperty(value = "年龄", dataType = "int", required = true)
    private int age;

    @ApiModelProperty(value = "性别", dataType = "int", required = true)
    private int gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
