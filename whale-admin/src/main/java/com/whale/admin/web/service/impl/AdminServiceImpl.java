package com.whale.admin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whale.admin.web.service.IAdminService;
import com.whale.framework.repository.mapper.admin.UserMapper;
import com.whale.framework.repository.model.admin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author trendong
 */
@Service
public class AdminServiceImpl extends ServiceImpl<UserMapper, User> implements IAdminService {

    @Resource
    @Qualifier("adminUserMapper")
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<User> list = userMapper.selectList(queryWrapper);
        return list;
    }

}
