package com.lucky.share.mapper;

import com.lucky.share.domain.BonusDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Kevin.Chen
 * @date 2019/03/24
 */
@Mapper
public interface BonusDetailMapper {

    /**
     * 获取明细
     * @param uid
     * @return
     */
    List<BonusDetail> getDetail(@Param("uid") Integer uid,@Param("start") Integer start,@Param("size") Integer size);

    /**
     * 插入详情
     * @param detail
     * @return
     */
    Integer insert(BonusDetail detail);
}
