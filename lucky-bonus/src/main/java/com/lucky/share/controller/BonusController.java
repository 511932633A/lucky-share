package com.lucky.share.controller;

import com.lucky.share.annotation.UserId;
import com.lucky.share.constant.ErrorCode;
import com.lucky.share.convert.AjaxResult;
import com.lucky.share.dto.TranDto;
import com.lucky.share.service.BonusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Kevin.Chen
 * @date 2019/3/26.
 */
@Api(tags = "积分API")
@RestController
@RequestMapping("/bonus")
public class BonusController {
    @Autowired
    private BonusService bonusService;

    @GetMapping("/hello")
    public String hello(@UserId Integer uid) {
        return "hello-" + uid;
    }

    /**
     * 创建账号
     */
    @ApiOperation(value = "创建账号")
    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public AjaxResult<Object> create(@UserId Integer uid) {
        return bonusService.create(uid);
    }

    /**
     * 交易
     * @param uid
     * @param tranDto
     * @return
     */
    @ApiOperation(value = "交易", notes = "交易类型为：2.签到 3.兑换")
    @ResponseBody
    @RequestMapping(value = "/tran", method = RequestMethod.POST)
    public AjaxResult<Object> tran(@UserId Integer uid, @RequestBody TranDto tranDto) {
        return bonusService.tran(uid, tranDto);
    }

    /**
     * 获取积分明细
     * @param uid
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "积分明细")
    @ResponseBody
    @GetMapping(value = "/detail")
    public AjaxResult<List> detail(@UserId Integer uid,
                                   @RequestParam(value = "page", defaultValue = "0") Integer page,
                                   @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return bonusService.getDetail(uid, page, size);
    }

    /**
     * 签到
     */
    @ApiOperation(value = "签到")
    @ResponseBody
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public AjaxResult<Object> sign(@UserId Integer uid) {
        // TODO: 签到
        return new AjaxResult().create(ErrorCode.UNSUPPORTED);
    }
}
