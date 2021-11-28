package com.whale.admin.web.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.annotations.VisibleForTesting;
import com.whale.admin.web.controller.system.vo.SysRoleCreateReqVO;
import com.whale.admin.web.service.system.ISysRoleService;
import com.whale.framework.repository.common.exception.util.ServiceExceptionUtil;
import com.whale.framework.repository.mapper.krplus.SysRoleMapper;
import com.whale.framework.repository.model.krplus.SysRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

import static com.whale.admin.web.controller.system.enums.SysErrorCodeConstants.ROLE_CODE_DUPLICATE;
import static com.whale.admin.web.controller.system.enums.SysErrorCodeConstants.ROLE_NAME_DUPLICATE;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

    private static Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public Long createRole(SysRoleCreateReqVO reqVO) {
        // 校验角色
        checkDuplicateRole(reqVO.getName(), reqVO.getCode(), null);
        // 插入到数据库
//        SysRoleDO role = SysRoleConvert.INSTANCE.convert(reqVO);
//        role.setType(SysRoleTypeEnum.CUSTOM.getType());
//        role.setStatus(CommonStatusEnum.ENABLE.getStatus());
//        roleMapper.insert(role);
        // 发送刷新消息
//        roleProducer.sendRoleRefreshMessage();
        // 返回
        return 1l;
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
        SysRole role = roleMapper.selectOne(new QueryWrapper<SysRole>().eq("name", name));
        if (role != null && !role.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(ROLE_NAME_DUPLICATE, name);
        }
        // 2. 是否存在相同编码的角色
        if (!StringUtils.hasText(code)) {
            return;
        }
        // 该 code 编码被其它角色所使用
        role = roleMapper.selectOne(new QueryWrapper<SysRole>().eq("code", name));
        if (role != null && !role.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(ROLE_CODE_DUPLICATE, code);
        }
    }

}
