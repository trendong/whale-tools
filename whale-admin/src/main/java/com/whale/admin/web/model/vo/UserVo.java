package com.whale.admin.web.model.vo;

/**
 * 用户视图
 *
 * @author trendong
 */
public class UserVo {

    /** 用户编号 **/
    private Integer id;
    /** 用户名 **/
    private String username;
    /** 年龄 **/
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
