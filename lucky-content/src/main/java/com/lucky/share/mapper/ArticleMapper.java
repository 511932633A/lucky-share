package com.lucky.share.mapper;

import com.lucky.share.domain.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Kevin.Chen
 * @date 2019/03/28
 */
@Mapper
public interface ArticleMapper {

    /**
     * 获取资源
     */
    Article getArticle(@Param("aid") Integer aid);

    /**
     * 创建资源
     * @param article
     * @return
     */
    Integer create(Article article);

    /**
     * 获取最近的资料列表
     * @param start
     * @param size
     * @return
     */
    List<Article> getNewArticles(@Param("start")Integer start, @Param("size")Integer size);

    /**
     * 获取投稿列表
     * @param uid
     * @param start
     * @param size
     * @return
     */
    List<Article>getMyArticles(@Param("uid")Integer uid, @Param("start")Integer start, @Param("size")Integer size);
}
