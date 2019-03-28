package com.lucky.share.filter;

import com.lucky.share.annotation.UserId;
import com.lucky.share.constant.ErrorCode;
import com.lucky.share.exception.BizException;
import com.lucky.share.utils.JwtUtil;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author Kevin.Chen
 * @date 2019/3/26.
 */
public class UserIdResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(UserId.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String authorization = nativeWebRequest.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            // 该接口需要uid
            throw new BizException(ErrorCode.TOKEN_NOTFIND);
        }
        String strUid = JwtUtil.getSub(authorization);

        if (StringUtils.isEmpty(strUid)) {
            throw new BizException(ErrorCode.TOKEN_INVALID);
        }

        return Integer.parseInt(strUid);
    }
}

