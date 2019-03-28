package com.lucky.share.mapper;

import com.lucky.share.domain.Notice;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Kevin.Chen
 * @date 2019/03/28
 */
@Mapper
public interface NoticeMapper {
    /**
     * 获取最新的通知
     * @return
     */
    Notice getNotice();
}
