package com.whale.admin.web.controller;

import com.whale.admin.web.service.IAdminService;
import com.whale.framework.repository.model.admin.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author trendong
 */
@Api(tags = "管理后台")
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private IAdminService iAdminService;

    /**
     * 获取用户列表
     *
     * @return
     */
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/user")
    public List<User> getUserList() {
        List<User> data = iAdminService.getUserList();
        return data;
    }

}
