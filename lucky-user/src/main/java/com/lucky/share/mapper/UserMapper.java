package com.lucky.share.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.lucky.share.domain.User;
/**
 * @author Kevin.Chen
 * @date 2019/03/24
 */
@Mapper
public interface UserMapper {
    /**
     * 创建
     */
    int addUser(User user);

    /**
     * 获取用户信息
     */
    User getUserByOpenId(@Param("openId")String openId);
}
