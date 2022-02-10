package com.whale.admin.web.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(description="用户实体")
public class User implements Serializable {

    @ApiModelProperty("用户编号")
    private Long id;
    @NotNull
    @ApiModelProperty("用户姓名")
    private String name;
    @NotNull
    @ApiModelProperty("用户年龄")
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}