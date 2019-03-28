package com.lucky.share.controller;

import com.lucky.share.annotation.UserId;
import com.lucky.share.constant.ErrorCode;
import com.lucky.share.convert.AjaxResult;
import com.lucky.share.dto.LoginRequest;
import com.lucky.share.feign.BonusClient;
import com.lucky.share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Kevin.Chen
 * @date 2019/3/26.
 */
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult<Map> login(@RequestBody LoginRequest loginRequest) {
        Map data = userService.login(loginRequest);

        if (data == null) {
            return new AjaxResult().create(ErrorCode.LOGIN_FAIL);
        }
        return new AjaxResult().create(ErrorCode.SUCCESS, data);
    }
}
