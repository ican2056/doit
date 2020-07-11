package com.github.ican2056.doit.biz.captcha;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
@ConditionalOnBean(CaptchaConfig.class)
public class CaptchaAutoConfiguration {

    @Resource(name = "captchaConfig")
    CaptchaConfig config;

    @Bean
    public CaptchaController config(){
        CaptchaController captchaController = new CaptchaController();
        captchaController.setWidth(config.getWidth());
        captchaController.setHeight(config.getHeight());
        captchaController.setCodeCount(config.getCodeCount());
        captchaController.setThickness(config.getThickness());
        captchaController.setSessionCaptchaKey(config.getSessionCaptchaKey());
        return captchaController;
    }

}
