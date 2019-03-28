package com.lucky.share.mapper;

import com.lucky.share.domain.Bonus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Kevin.Chen
 * @date 2019/03/24
 */
@Mapper
public interface BonusMapper {
    /**
     * 创建
     * @param bonus
     * @return
     */
    int insert(Bonus bonus);

    /**
     * 获取余额
     * @param uid
     * @return
     */
    int getBalance(Integer uid);

    /**
     * 更新余额
     * @param uid
     * @param volume
     * @return
     */
    Integer updateBalance(@Param("uid")Integer uid, @Param("volume")Integer volume);
}
