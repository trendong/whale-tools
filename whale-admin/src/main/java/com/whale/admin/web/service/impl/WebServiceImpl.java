package com.whale.admin.web.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whale.admin.web.service.IWebService;
import com.whale.framework.repository.mapper.web.UserMapper;
import com.whale.framework.repository.model.web.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author trendong
 */
@Service
public class WebServiceImpl extends ServiceImpl<UserMapper, User> implements IWebService {

    @Resource
    @Qualifier("webUserMapper")
    private UserMapper userMapper;

    @Override
    @DS("slave_1")
    public List<User> getUserList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<User> list = userMapper.selectList(queryWrapper);
        return list;
    }

}
