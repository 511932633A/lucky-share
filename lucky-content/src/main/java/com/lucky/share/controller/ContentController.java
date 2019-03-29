package com.lucky.share.controller;

import com.lucky.share.annotation.UserId;
import com.lucky.share.constant.ErrorCode;
import com.lucky.share.convert.AjaxResult;
import com.lucky.share.domain.Article;
import com.lucky.share.dto.ArticleDto;
import com.lucky.share.service.ContentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kevin.Chen
 * @date 2019/3/28.
 */
@RestController
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @ApiOperation(value = "获取最新文章")
    @GetMapping("/articles")
    public AjaxResult<List> articles(@RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new AjaxResult<List>().create(ErrorCode.SUCCESS, contentService.getNewArticles(page, size));
    }

    @ApiOperation(value = "获取最新通知")
    @GetMapping("/notice")
    public AjaxResult<Object> notice() {
        return new AjaxResult<>().create(ErrorCode.SUCCESS, contentService.getNewNotice());
    }

    @ApiOperation(value = "兑换")
    @RequestMapping("/exchange")
    public AjaxResult<Object> exchange(@UserId Integer uid, @RequestBody Integer aid) {
        return new AjaxResult<>().create(ErrorCode.SUCCESS, contentService.exchange(uid, aid));
    }

    @ApiOperation(value = "我的兑换")
    @GetMapping("/exchange/me")
    public AjaxResult<List> getMyExchange(@UserId Integer uid,
                                            @RequestParam(value = "page", defaultValue = "0") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new AjaxResult<List>().create(ErrorCode.SUCCESS, contentService.getExchangeList(uid, page, size));
    }

    @ApiOperation(value = "投稿")
    @GetMapping("/contribute")
    public AjaxResult<Object> contribute(@UserId Integer uid, @RequestBody ArticleDto article) {
        ErrorCode code = contentService.contribute(uid, article);
        return new AjaxResult<>().create(code);
    }

    @ApiOperation(value = "我的投稿")
    @GetMapping("/contribute/me")
    public AjaxResult<List> getMyContrbute(@UserId Integer uid,
                                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return new AjaxResult<List>().create(ErrorCode.SUCCESS, contentService.getMyContrbute(uid, page, size));
    }
}
