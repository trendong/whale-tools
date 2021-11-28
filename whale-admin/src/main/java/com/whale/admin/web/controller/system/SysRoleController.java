package com.whale.admin.web.controller.system;

import com.whale.admin.web.controller.system.vo.SysRoleCreateReqVO;
import com.whale.admin.web.service.system.ISysRoleService;
import com.whale.framework.repository.common.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.whale.framework.repository.common.vo.CommonResult.success;

@Api(tags = "角色")
@RestController
@RequestMapping("/system/role")
@Validated
public class SysRoleController {

    @Resource
    private ISysRoleService iSysRoleService;

    @PostMapping("/create")
    @ApiOperation("创建角色")
    public CommonResult<Long> createRole(@Valid @RequestBody SysRoleCreateReqVO reqVO) {
        return success(iSysRoleService.createRole(reqVO));
    }

//    @PutMapping("/update")
//    @ApiOperation("修改角色")
//    public CommonResult<Boolean> updateRole(@Valid @RequestBody SysRoleUpdateReqVO reqVO) {
//        iSysRoleService.updateRole(reqVO);
//        return success(true);
//    }
//
//    @PutMapping("/update-status")
//    @ApiOperation("修改角色状态")
//    public CommonResult<Boolean> updateRoleStatus(@Valid @RequestBody SysRoleUpdateStatusReqVO reqVO) {
//        iSysRoleService.updateRoleStatus(reqVO.getId(), reqVO.getStatus());
//        return success(true);
//    }
//
//    @DeleteMapping("/delete")
//    @ApiOperation("删除角色")
//    @ApiImplicitParam(name = "id", value = "角色编号", required = true, example = "1024", dataTypeClass = Long.class)
//    public CommonResult<Boolean> deleteRole(@RequestParam("id") Long id) {
//        iSysRoleService.deleteRole(id);
//        return success(true);
//    }
//
//    @GetMapping("/get")
//    @ApiOperation("获得角色信息")
//    public CommonResult<SysRoleRespVO> getRole(@RequestParam("id") Long id) {
//        SysRoleDO role = iSysRoleService.getRole(id);
//        return success(SysRoleConvert.INSTANCE.convert(role));
//    }
//
//    @GetMapping("/page")
//    @ApiOperation("获得角色分页")
//    public CommonResult<PageResult<SysRoleDO>> getRolePage(SysRolePageReqVO reqVO) {
//        return success(iSysRoleService.getRolePage(reqVO));
//    }
//
//    @GetMapping("/list-all-simple")
//    @ApiOperation(value = "获取角色精简信息列表", notes = "只包含被开启的角色，主要用于前端的下拉选项")
//    public CommonResult<List<SysRoleSimpleRespVO>> getSimpleRoles() {
//        // 获得角色列表，只要开启状态的
//        List<SysRoleDO> list = iSysRoleService.getRoles(Collections.singleton(CommonStatusEnum.ENABLE.getStatus()));
//        // 排序后，返回个诶前端
//        list.sort(Comparator.comparing(SysRoleDO::getSort));
//        return success(SysRoleConvert.INSTANCE.convertList02(list));
//    }
//
//    @GetMapping("/export")
//    @OperateLog(type = EXPORT)
//    @PreAuthorize("@ss.hasPermission('system:role:export')")
//    public void export(HttpServletResponse response, @Validated SysRoleExportReqVO reqVO) throws IOException {
//        List<SysRoleDO> list = iSysRoleService.getRoles(reqVO);
//        List<SysRoleExcelVO> data = SysRoleConvert.INSTANCE.convertList03(list);
//        // 输出
//        ExcelUtils.write(response, "角色数据.xls", "角色列表", SysRoleExcelVO.class, data);
//    }
}
