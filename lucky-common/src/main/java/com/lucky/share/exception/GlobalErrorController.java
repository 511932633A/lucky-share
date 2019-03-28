package com.lucky.share.exception;

import com.lucky.share.constant.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kevin.Chen
 * 统一异常管理
 */
@Controller
@RequestMapping({"${server.error.path:${error.path:/error}}"})
@Slf4j
public class GlobalErrorController extends BasicErrorController {

    public static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";

    @Autowired
    public GlobalErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        super(errorAttributes, serverProperties.getError());
    }

    @RequestMapping(produces = "text/html")
    @Override
    public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {
        return super.errorHtml(request, response);
    }

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Throwable exception = this.getException(request);

        Map map = new HashMap<>(3);
        ErrorCode errorCode = ErrorCode.UNKNOWN;
        if (exception instanceof BizException) {
            BizException exp = (BizException) exception;
            errorCode = exp.getErrorCode();
        }
        map.put("code", errorCode.getCode());
        map.put("msg", exception == null ? errorCode.getMsg() : exception.getMessage());
        map.put("data", null);

        // Http Status都是200，错误码在code
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

    private Throwable getException(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Throwable exception = getAttribute(
                requestAttributes,
                ERROR_ATTRIBUTE
        );

        if (exception == null) {
            exception = getAttribute(requestAttributes, "javax.servlet.error.exception");
        }
        return exception;
    }
}
