package com.whale.admin.web.repository.model.vo;

/**
 * 用户视图
 *
 * @author trendong
 */
public class UserDetailVo {

    /** 用户编号 **/
    private Integer userId;
    /** 用户名 **/
    private String username;
    /** 年龄 **/
    private Integer age;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
