package com.lucky.share.feign;

import com.lucky.share.constant.ErrorCode;
import com.lucky.share.convert.AjaxResult;
import com.lucky.share.dto.TranDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kevin.Chen
 * @date 2019/3/27.
 */
@FeignClient(name = "lucky-bonus", fallback = BonusFallback.class)
public interface BonusClient {
    @GetMapping("/bonus/hello")
    @HystrixCommand(fallbackMethod="getFallback")
    String hello();

    /**
     * 创建账号
     */
    @ResponseBody
    @RequestMapping(value = "/bonus/create", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod="getFallback")
    AjaxResult<Object> create(@RequestHeader("Authorization") String token);

    /**
     * 交易
     * @param tranDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bonus/tran", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod="getFallback")
    AjaxResult<Object> tran(@RequestBody TranDto tranDto);
}

@Component
class BonusFallback implements BonusClient {

    @Override
    public String hello() {
        return "feign client error!";
    }

    @Override
    public AjaxResult<Object> create(@RequestHeader("Authorization") String token) {
        return new AjaxResult<>().create(ErrorCode.BONUS_UNAVAILABLE);
    }

    @Override
    public AjaxResult<Object> tran(@RequestBody TranDto tranDto) {
        return new AjaxResult<>().create(ErrorCode.BONUS_UNAVAILABLE);
    }

}

