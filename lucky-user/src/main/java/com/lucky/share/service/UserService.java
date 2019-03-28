package com.lucky.share.service;


import com.lucky.share.constant.ErrorCode;
import com.lucky.share.convert.AjaxResult;
import com.lucky.share.domain.User;
import com.lucky.share.dto.LoginRequest;
import com.lucky.share.feign.BonusClient;
import com.lucky.share.mapper.UserMapper;
import com.lucky.share.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Kevin.Chen
 * @date 2019/03/24
 */
@Service
public class UserService{
    /**
     * 默认7天
      */
    @Value("${token.duration.time:604800}")
    private Long tokenDurationTime;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BonusClient bonusClient;
    /**
     * 登录
     * @return
     */
    public Map login(LoginRequest request){
        // TODO: 这里需要通过code，去腾讯哪获取唯一的openid
        String openId = queryOpenId(request.getCode());
        // 检查库里是否存在
        User user = userMapper.getUserByOpenId(openId);
        if (user == null) {
            user = new User();
            user.setAvatarUrl(request.getAvatarUrl());
            user.setCreateTime(new Date());
            user.setGender(request.getGender());
            user.setNickName(request.getNickName());
            user.setOpenId(openId);
            userMapper.addUser(user);
        }

        // 创建账号
        // TODO: 这里存在事务问题，如果创建账号失败，是否回滚
        // 1.这里可以往另外一个表中记录个标示，让人工介入创建该账号。
        // 2.由于账号没创建成功，这登录不成功。
        AjaxResult result = bonusClient.create();
        if (result.getCode().equals(ErrorCode.SUCCESS.getCode())) {
            // 生成token
            String t = JwtUtil.buildJWT(String.valueOf(user.getId()), tokenDurationTime * 1000);
            Map data = new HashMap<>(2);
            Map tokenInfo = new HashMap<>(2);
            tokenInfo.put("token", t);
            tokenInfo.put("expirationTime", System.currentTimeMillis() + tokenDurationTime * 1000);
            data.put("token", tokenInfo);

            Map userInfo = new HashMap<>(2);
            userInfo.put("nickName", user.getNickName());
            userInfo.put("avatarUrl", user.getAvatarUrl());
            userInfo.put("gender",user.getGender());
            data.put("user", userInfo);
            return data;
        } else {
            return null;
        }
    }

    private String queryOpenId(String code) {
        //TODO: 根据code，去登录服务器请求open_id
        // 这里暂时返回随机
        return UUID.randomUUID().toString().replace("-", "");
    }
}
