package com.whale.admin.web.service.system;

import com.whale.admin.web.controller.system.vo.SysRoleCreateReqVO;
import com.whale.admin.web.controller.system.vo.SysRoleUpdateReqVO;
import com.whale.framework.repository.model.krplus.SysRole;

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

    /**
     * 更新角色
     *
     * @param reqVO 更新角色信息
     */
    void updateRole(SysRoleUpdateReqVO reqVO);

    /**
     * 更新角色状态
     *
     * @param id 角色编号
     * @param status 状态
     */
    void updateRoleStatus(Long id, Integer status);

    /**
     * 删除角色
     *
     * @param id 角色编号
     */
    void deleteRole(Long id);

    /**
     * 获得角色
     *
     * @param id 角色编号
     * @return 角色
     */
    SysRole getRole(Long id);
}
