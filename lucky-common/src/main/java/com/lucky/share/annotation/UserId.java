package com.lucky.share.annotation;

import java.lang.annotation.*;

/**
 * @author Kevin.Chen
 * @date 2019/3/26.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserId {
}
