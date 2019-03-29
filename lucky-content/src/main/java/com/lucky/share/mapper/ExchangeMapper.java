package com.lucky.share.mapper;

import com.lucky.share.domain.Article;
import com.lucky.share.domain.Exchange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Kevin.Chen
 * @date 2019/03/28
 */
@Mapper
public interface ExchangeMapper {

    /**
     * 获取投稿列表
     * @param uid
     * @param start
     * @param size
     * @return
     */
    List<Article> getMyExchange(@Param("uid") Integer uid, @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 兑换
     * @param exchange
     * @return
     */
    Integer create(Exchange exchange);
}
