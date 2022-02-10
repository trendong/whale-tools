package com.whale.framework.repository.mapper.krplus;

import com.whale.framework.mybatis.core.mapper.BaseMapperX;
import com.whale.framework.mybatis.core.query.QueryWrapperX;
import com.whale.framework.repository.common.vo.PageResult;
import com.whale.framework.repository.common.vo.system.SysRoleExportReqVO;
import com.whale.framework.repository.common.vo.system.SysRolePageReqVO;
import com.whale.framework.repository.model.krplus.SysRole;
import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author trendong
 * @since 2021-11-27
 */
public interface SysRoleMapper extends BaseMapperX<SysRole> {

    default PageResult<SysRole> selectPage(SysRolePageReqVO reqVO) {
        return selectPage(reqVO, new QueryWrapperX<SysRole>().likeIfPresent("name", reqVO.getName())
                .likeIfPresent("code", reqVO.getCode())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }

    default SysRole selectByName(String name) {
        return selectOne(new QueryWrapperX<SysRole>().eq("name", name));
    }

    default SysRole selectByCode(String code) {
        return selectOne(new QueryWrapperX<SysRole>().eq("code", code));
    }

    default List<SysRole> selectListByStatus(@Nullable Collection<Integer> statuses) {
        return selectList(new QueryWrapperX<SysRole>().in("status", statuses));
    }

    default List<SysRole> listRoles(SysRoleExportReqVO reqVO) {
        return selectList(new QueryWrapperX<SysRole>().likeIfPresent("name", reqVO.getName())
                .likeIfPresent("code", reqVO.getCode())
                .eqIfPresent("status", reqVO.getStatus())
                .betweenIfPresent("create_time", reqVO.getBeginTime(), reqVO.getEndTime()));
    }


}
