package com.lucky.share.feign;

import com.lucky.share.constant.ErrorCode;
import com.lucky.share.convert.AjaxResult;
import com.lucky.share.dto.TranDto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kevin.Chen
 * @date 2019/3/27.
 */
@FeignClient(name = "lucky-bonus", fallback = BonusFallback.class)
public interface BonusClient {
    /**
     * 交易
     * @param tranDto
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tran", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod="getFallback")
    AjaxResult<Object> tran(@RequestBody TranDto tranDto);
}

@Component
class BonusFallback implements BonusClient {

    @Override
    public AjaxResult<Object> tran(@RequestBody TranDto tranDto) {
        return new AjaxResult<>().create(ErrorCode.BONUS_UNAVAILABLE);
    }

}

