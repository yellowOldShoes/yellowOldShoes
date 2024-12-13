package com.huangsf.ums.util;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.GifCaptcha;
import org.springframework.stereotype.Component;


/**
 * @author huangsf
 * @create 2024-12-02  9:37
 */
@Component
public class CaptchaUtils {

    public CircleCaptcha getCaptcha(){
        return CaptchaUtil.createCircleCaptcha(160, 60);
    }

    public GifCaptcha getCaptchaGif(){
        return CaptchaUtil.createGifCaptcha(160, 60);
    }

}
