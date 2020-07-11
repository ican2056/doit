package com.github.ican2056.doit.biz.captcha;

import lombok.Data;

@Data
/**
 * 使用方法，配置CaptchaConfig到spring容器，然后登陆方法上加上CaptchaCheck注解
 */
public class CaptchaConfig {

    /**
     * 图片宽
     */
    private int width = 200;
    /**
     * 图片高
     */
    private int height = 40;
    /**
     * 字符个数
     */
    private int codeCount = 4;
    /**
     * 干扰线宽度
     */
    private int thickness = 3;

    /**
     * session中存储用的key
     */
    private String sessionCaptchaKey = "sessionCaptchaKey";

    /**
     * 验证码请求路径
     */
    private String path = "/showCaptcha";
}
