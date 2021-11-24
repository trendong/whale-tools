package com.whale.admin.web.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.whale.framework.repository.model.admin.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author trendong
 * @since 2021-11-24
 */
public interface IAdminService extends IService<User> {

    /**
     * 获取用户列表
     *
     * @return List<User>
     */
    List<User> getUserList();

}
