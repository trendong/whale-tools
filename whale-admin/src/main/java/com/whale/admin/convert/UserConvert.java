package com.whale.admin.convert;

import com.whale.admin.web.repository.model.vo.UserBo;
import com.whale.admin.web.repository.model.vo.UserDetailVo;
import com.whale.admin.web.repository.model.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * 用户实体对象转换
 *
 * @author trendong
 */
@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * 默认转换
     *
     * @param userBo obj
     * @return UserVo
     */
    UserVo convert(UserBo userBo);

    /**
     * 自定义属性映射转换
     *
     * @param userBo obj
     * @return UserDetailVo
     */
    @Mappings({
            @Mapping(source = "id", target = "userId")
    })
    UserDetailVo convertDetail(UserBo userBo);

}
