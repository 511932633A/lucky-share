package com.lucky.share.service;

import com.lucky.share.constant.ErrorCode;
import com.lucky.share.convert.AjaxResult;
import com.lucky.share.domain.Article;
import com.lucky.share.domain.Exchange;
import com.lucky.share.domain.Notice;
import com.lucky.share.dto.ArticleDto;
import com.lucky.share.dto.TranDto;
import com.lucky.share.feign.BonusClient;
import com.lucky.share.mapper.ArticleMapper;
import com.lucky.share.mapper.ExchangeMapper;
import com.lucky.share.mapper.NoticeMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kevin.Chen
 * @date 2019/03/24
 */
@Service
public class ContentService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ExchangeMapper exchangeMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private BonusClient bonusClient;

    /**
     * 获取最新的资料
     * @param page
     * @param size
     * @return
     */
    public List<Article> getNewArticles(Integer page, Integer size) {
        return articleMapper.getNewArticles(page * size, size);
    }

    /**
     * 获取最新通知
     * @return
     */
    public Notice getNewNotice() {
        return noticeMapper.getNotice();
    }

    /**
     * 兑换
     */
    @Transactional(rollbackFor = Exception.class)
    public ErrorCode exchange(Integer uid, Integer aid) {
        // 判断下资源是否存在
        Article article = articleMapper.getArticle(aid);
        if (article == null) {
            return ErrorCode.BONUS_PARAM_ERROR;
        }
        Exchange exchange = new Exchange();
        exchange.setUid(uid);
        exchange.setVolume(article.getPrice());
        exchange.setAid(aid);
        articleMapper.create(article);

        // TODO : 由于这里已经插入兑换表中，为了防止接下来扣款可能失败，这里可以往一个临时表
        // TODO : 记录下，如果扣款成功，则删除临时表。扣款失败，由客服介入扣款。
        // 或者可以在tb_exchange增加个状态标识，来标识该交易是否完成。
        // 提交扣款
        TranDto tran = new TranDto();
        tran.setVolume(-article.getPrice());
        tran.setTranType(3);
        tran.setRemark(String.format("兑换资源：%d", aid));
        bonusClient.tran(tran);
        return ErrorCode.SUCCESS;
    }

    /**
     * 获取兑换列表
     * @param uid
     * @param page
     * @param size
     * @return
     */
    public List<Article> getExchangeList(Integer uid, Integer page, Integer size) {
        return exchangeMapper.getMyExchange(uid, page * size, size);
    }

    /**
     * 投稿
     * @param uid
     * @param articleDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ErrorCode contribute(Integer uid, ArticleDto articleDto) {
        Article article = new Article();
        BeanUtils.copyProperties(articleDto, article);
        article.setUid(uid);
        articleMapper.create(article);
        return ErrorCode.SUCCESS;
    }

    /**
     * 获取投稿列表
     * @param uid
     * @param page
     * @param size
     * @return
     */
    public List<Article> getMyContrbute(Integer uid, Integer page, Integer size) {
        return articleMapper.getMyArticles(uid, page * size, size);
    }
}
