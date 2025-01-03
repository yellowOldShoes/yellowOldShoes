package com.huangsf.ums.controller;

import cn.hutool.captcha.CircleCaptcha;
import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ErrorCode;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.dto.RegisterDto;
import com.huangsf.ums.exception.BusinessException;
import com.huangsf.ums.service.UserService;
import com.huangsf.ums.util.CaptchaUtils;
import com.huangsf.ums.util.CurrentUser;
import com.huangsf.ums.util.JwtUtils;
import com.huangsf.ums.vo.LoginVo;
import io.swagger.annotations.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author huangsf
 * @create 2024-11-26  15:40
 */
@Api(tags = "用户接口集")
@RestController
@RequestMapping("/user")
public class UserManagerController {

    @Resource
    CaptchaUtils captchaUtils;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserService userService;

    /**
     * 注册
     */
    @ApiOperation(value = "注册",notes = "注册用户的接口，参数是必填")
    @PostMapping("/register")
    public BaseResponse register(@RequestBody RegisterDto registerDto) {

        if(registerDto==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        long result = userService.userRegister(registerDto);
        return ResultUtils.success(result);
    }

    /**
     * 获取验证码
     * //TODO 添加防止接口攻击，限制不同ip的调用评率
     */
    @GetMapping(value = "/getCapture",produces = "image/png")
    @ApiOperation(value = "验证码",notes = "获取验证码")
    public void getCapture(HttpServletResponse response) throws IOException {

        ServletOutputStream out = null;

        try {
            //禁止浏览器缓存验证码图片的响应头
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/png");
            CircleCaptcha captcha = captchaUtils.getCaptcha();
            String code = captcha.getCode();
            stringRedisTemplate.opsForValue().set(code,code,10, TimeUnit.MINUTES);
            out = response.getOutputStream();
            ImageIO.write(captcha.getImage(), "png", out);
            out.flush();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out!=null){
                out.close();
            }
        }
    }


    /**
     * 登录
     */
    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "属于必须填写的")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "Authorization", value = "用户认证令牌", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer token")
//    })

    public BaseResponse login(@ApiParam(name = "username",value = "用户名") @RequestParam("username") String username,
                              @ApiParam(name="password",value = "密码") @RequestParam("password")String password,
                              @ApiParam(name="capture",value="验证码") @RequestParam("capture")String capture){

        LoginVo loginResult = userService.login(username, password, capture);
        return ResultUtils.success(loginResult);
    }


    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("/list")
    @ApiOperation(value = "用户列表",notes = "属于必须填写的")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "用户认证令牌", required = true, dataType = "string", paramType = "header", defaultValue = "Bearer token")
    })
    public BaseResponse list(@RequestHeader("Authorization") String auth){
        CurrentUser currentUser = JwtUtils.getCurrentUser(auth);


        return ResultUtils.success(currentUser);
    }

    @GetMapping("/getCurrentUser")
    @ApiOperation(value = "获取当前登录用户")
    public BaseResponse getCurrentUser(@RequestHeader("Authorization") String auth){

        CurrentUser currentUser = JwtUtils.getCurrentUser(auth);
        return ResultUtils.success(currentUser);
    }

    /**
     * 添加用户
     * @return
     */
    public BaseResponse addUser(@RequestHeader("Authorization") String auth){





        return null;
    }

    /**
     * 更新用户
     * @return
     */
    public BaseResponse update(){

        return null;
    }

}
