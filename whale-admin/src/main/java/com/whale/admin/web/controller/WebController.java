package com.whale.admin.web.controller;

import com.whale.admin.web.service.IWebService;
import com.whale.framework.repository.model.web.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author trendong
 */
@Api(tags = "web")
@RestController
@RequestMapping(value = "/web")
public class WebController {

    @Resource
    private IWebService iWebService;

    /**
     * 获取用户列表
     *
     * @return
     */
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/user")
    public List<User> getUserList() {
        List<User> data = iWebService.getUserList();
        return data;
    }

}
