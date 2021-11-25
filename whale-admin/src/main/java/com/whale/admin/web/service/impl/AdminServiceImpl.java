package com.whale.admin.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.whale.admin.web.service.IAdminService;
import com.whale.framework.repository.mapper.admin.UserMapper;
import com.whale.framework.repository.model.admin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author trendong
 */
@Service
public class AdminServiceImpl extends ServiceImpl<UserMapper, User> implements IAdminService {

    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Resource
    @Qualifier("adminUserMapper")
    private UserMapper adminUserMapper;

    @Override
    public List<User> getUserList() {
        QueryWrapper queryWrapper = new QueryWrapper();
        List<User> list = adminUserMapper.selectList(queryWrapper);
        List<Map<String, Object>> mapList = adminUserMapper.selectUserByWhere();
        logger.debug("getUserList -> mapList： {}", mapList);
        return list;
    }

}