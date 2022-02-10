package com.whale.admin.web.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.google.common.annotations.VisibleForTesting;
import com.whale.admin.web.system.convert.SysRoleConvert;
import com.whale.admin.web.system.enums.SysRoleTypeEnum;
import com.whale.admin.web.system.vo.SysRoleCreateReqVO;
import com.whale.admin.web.system.vo.SysRoleUpdateReqVO;
import com.whale.admin.web.system.service.ISysRoleService;
import com.whale.framework.common.enums.CommonStatusEnum;
import com.whale.framework.repository.common.exception.util.ServiceExceptionUtil;
import com.whale.framework.repository.common.vo.PageResult;
import com.whale.framework.repository.common.vo.system.SysRoleExportReqVO;
import com.whale.framework.repository.common.vo.system.SysRolePageReqVO;
import com.whale.framework.repository.mapper.krplus.SysRoleMapper;
import com.whale.framework.repository.model.krplus.SysRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;

import java.util.Collection;
import java.util.List;

import static com.whale.admin.web.system.enums.SysErrorCodeConstants.*;


@Service
@DS("slave_2")
public class SysRoleServiceImpl implements ISysRoleService {

    private static Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public Long createRole(SysRoleCreateReqVO reqVO) {
        // 校验角色
        checkDuplicateRole(reqVO.getName(), reqVO.getCode(), null);
        // 插入到数据库
        SysRole role = SysRoleConvert.INSTANCE.convert(reqVO);
        role.setType(SysRoleTypeEnum.CUSTOM.getType());
        role.setStatus(CommonStatusEnum.ENABLE.getStatus());
        roleMapper.insert(role);
        return role.getId();
    }

    @Override
    public void updateRole(SysRoleUpdateReqVO reqVO) {
        // 校验是否可以更新
        this.checkUpdateRole(reqVO.getId());
        // 校验角色的唯一字段是否重复
        checkDuplicateRole(reqVO.getName(), reqVO.getCode(), reqVO.getId());
        // 更新到数据库
        SysRole updateObject = SysRoleConvert.INSTANCE.convert(reqVO);
        roleMapper.updateById(updateObject);
    }

    @Override
    public void updateRoleStatus(Long id, Integer status) {
        // 校验是否可以更新
        this.checkUpdateRole(id);
        // 更新状态
        SysRole updateObject = new SysRole();
        updateObject.setId(id);
        updateObject.setStatus(status);
        roleMapper.updateById(updateObject);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long id) {
        // 校验是否可以更新
        this.checkUpdateRole(id);
        // 标记删除
        roleMapper.deleteById(id);
        // 删除相关数据
//        permissionService.processRoleDeleted(id);
    }

    @Override
    public SysRole getRole(Long id) {
        return roleMapper.selectById(id);
    }

    @Override
    public PageResult<SysRole> getRolePage(SysRolePageReqVO reqVO) {
        return roleMapper.selectPage(reqVO);
    }

    @Override
    public List<SysRole> getRoles(@Nullable Collection<Integer> statuses) {
        return roleMapper.selectListByStatus(statuses);
    }

    @Override
    public List<SysRole> getRoles(SysRoleExportReqVO reqVO) {
        return roleMapper.listRoles(reqVO);
    }

    /**
     * 校验角色是否可以被更新
     *
     * @param id 角色编号
     */
    @VisibleForTesting
    public void checkUpdateRole(Long id) {
        SysRole roleDO = roleMapper.selectById(id);
        if (roleDO == null) {
            throw ServiceExceptionUtil.exception(ROLE_NOT_EXISTS);
        }
        // 内置角色，不允许删除
        if (SysRoleTypeEnum.SYSTEM.getType().equals(roleDO.getType())) {
            throw ServiceExceptionUtil.exception(ROLE_CAN_NOT_UPDATE_SYSTEM_TYPE_ROLE);
        }
    }

    /**
     * 校验角色的唯一字段是否重复
     *
     * 1. 是否存在相同名字的角色
     * 2. 是否存在相同编码的角色
     *
     * @param name 角色名字
     * @param code 角色编码
     * @param id 角色编号
     */
    @VisibleForTesting
    public void checkDuplicateRole(String name, String code, Long id) {
        // 1. 该 name 名字被其它角色所使用
        SysRole role = roleMapper.selectByName(name);
        if (role != null && !role.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(ROLE_NAME_DUPLICATE, name);
        }
        // 2. 是否存在相同编码的角色
        if (!StringUtils.hasText(code)) {
            return;
        }
        // 该 code 编码被其它角色所使用
        role = roleMapper.selectByCode(code);
        if (role != null && !role.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(ROLE_CODE_DUPLICATE, code);
        }
    }

}
