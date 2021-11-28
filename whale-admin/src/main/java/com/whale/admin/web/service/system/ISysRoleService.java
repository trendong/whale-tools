package com.whale.admin.web.service.system;

import com.whale.admin.web.controller.system.vo.SysRoleCreateReqVO;

/**
 * 角色 Service 接口
 *
 * @author trendong
 */
public interface ISysRoleService {

    /**
     * 创建角色
     *
     * @param reqVO 创建角色信息
     * @return 角色编号
     */
    Long createRole(SysRoleCreateReqVO reqVO);

}
