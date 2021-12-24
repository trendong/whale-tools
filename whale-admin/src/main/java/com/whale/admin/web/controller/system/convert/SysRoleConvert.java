package com.whale.admin.web.controller.system.convert;

import com.whale.admin.web.controller.system.vo.SysRoleCreateReqVO;
import com.whale.admin.web.controller.system.vo.SysRoleRespVO;
import com.whale.admin.web.controller.system.vo.SysRoleUpdateReqVO;
import com.whale.framework.repository.model.krplus.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysRoleConvert {

    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    SysRole convert(SysRoleCreateReqVO bean);
    SysRole convert(SysRoleUpdateReqVO bean);
    SysRoleRespVO convert(SysRole bean);


}
