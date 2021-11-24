package com.whale.framework.repository.mapper.web;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whale.framework.repository.model.web.User;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author trendong
 * @since 2021-11-22
 */
@Repository("webUserMapper")
public interface UserMapper extends BaseMapper<User> {

}
