package com.lucky.share.controller;

import com.lucky.share.annotation.UserId;
import com.lucky.share.constant.ErrorCode;
import com.lucky.share.convert.AjaxResult;
import com.lucky.share.dto.LoginRequest;
import com.lucky.share.feign.BonusClient;
import com.lucky.share.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author Kevin.Chen
 * @date 2019/3/26.
 */
@Api(tags = "用户API")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private BonusClient bonusClient;

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(@UserId Integer uid) {
        return bonusClient.hello();
    }

    @ApiOperation(value = "登录", notes = "这里由于未申请微信接口，openid为随机生成")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult<Map> login(HttpServletRequest request, @RequestBody LoginRequest loginRequest) {
        Map data = userService.login(request, loginRequest);

        if (data == null) {
            return new AjaxResult().create(ErrorCode.LOGIN_FAIL);
        }
        return new AjaxResult().create(ErrorCode.SUCCESS, data);
    }
}
