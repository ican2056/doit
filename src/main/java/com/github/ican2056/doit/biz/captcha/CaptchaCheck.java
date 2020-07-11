package com.github.ican2056.doit.biz.captcha;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CaptchaCheck {

    /**
     * 校验时取用户所输入验证码的key
     */
    String verifyCaptchaKey();

}
