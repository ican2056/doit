package com.github.ican2056.doit.biz.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Data
/**
 * 验证码控制器
 */
@Controller
@Aspect
public class CaptchaController {

    /**
     * 图片宽
     */
    private int width;
    /**
     * 图片高
     */
    private int height;
    /**
     * 字符个数
     */
    private int codeCount;
    /**
     * 干扰线宽度
     */
    private int thickness;

    /**
     * session中存储用的key
     */
    private String sessionCaptchaKey = "sessionCaptchaKey";

    @RequestMapping("#{captchaConfig.path}")
    public void showCaptcha(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(width, height, codeCount, thickness);

        //图形验证码写出，可以写出到文件，也可以写出到流
        ServletOutputStream outputStream = resp.getOutputStream();
        captcha.write(outputStream);
        outputStream.close();

        //存储到session以供验证
        session.setAttribute(sessionCaptchaKey, captcha);
    }

    /**
     * 执行控制器方法前校验验证码，验证码只能在 url 或 控制器方法参数列表的第一个参数 中
     */
    @Before(value = "@annotation(captchaCheck)")
    public void verifyCaptcha(JoinPoint joinPoint, CaptchaCheck captchaCheck) {
        String key = captchaCheck.verifyCaptchaKey();
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
        //优先从url取出
        String captchaValue = req.getParameter(key);
        if (captchaValue == null) {//其次从第一个参数取
            JSONObject jsonData = JSONUtil.parseObj(joinPoint.getArgs()[0]);
            List<String> keys = StrUtil.split(key, '.');//这里的key是全路径，从最外点到验证码的key
            for (int i = 0; i < keys.size() -1; i++) {
                String k = keys.get(i);
                jsonData = jsonData.getJSONObject(k);
            }
            captchaValue = jsonData.getStr(keys.get(keys.size() - 1));
        }
        ShearCaptcha captcha = (ShearCaptcha) req.getSession().getAttribute(sessionCaptchaKey);
        req.getSession().removeAttribute(sessionCaptchaKey);

        //验证图形验证码的有效性，返回boolean值
        if(null == captcha || !captcha.verify(captchaValue)){
            throw new RuntimeException("验证码错误!");
        }
    }

}
