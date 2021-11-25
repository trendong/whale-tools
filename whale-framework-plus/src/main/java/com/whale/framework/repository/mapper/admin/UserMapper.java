package com.whale.framework.repository.mapper.admin;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whale.framework.repository.model.admin.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author trendong
 * @since 2021-11-22
 */
@Repository("adminUserMapper")
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取自定义用户列表
     *
     * @param
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> selectUserByWhere();

}