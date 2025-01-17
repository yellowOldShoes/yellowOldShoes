package com.huangsf.ums.filter;

import cn.hutool.json.JSONUtil;
import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ErrorCode;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.config.WhiteListConfig;
import com.huangsf.ums.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author huangsf
 * @create 2024-12-09  15:04
 */

public class TokenFilter implements Filter {

    private StringRedisTemplate stringRedisTemplate;

    private WhiteListConfig whiteListConfig;

    private final AntPathMatcher pathMatcher = new AntPathMatcher(); // 支持通配符匹配

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void setWhiteListConfig(WhiteListConfig whiteListConfig) {
        this.whiteListConfig = whiteListConfig;
    }

    //
//    public void setWhiteListConfig(WhiteListConfig whiteListConfig) {
//        this.whiteListConfig = whiteListConfig;
//    }
    /**
     * 过滤器拦截到请求执行的方法:
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        //获取请求url接口
        String path = request.getServletPath();
        /*
          白名单请求都直接放行:
         */
        List<String> whiteList = whiteListConfig.getUrls();
        if(isWhiteListed(path,whiteList)){
            chain.doFilter(request, response);
            return;
        }
        /*
          其它请求都校验token:
         */
        //拿到前端归还的token
        String clientToken = request.getHeader(SystemConstant.HEADER_TOKEN_NAME);
        //校验token,校验通过请求放行
        if(StringUtils.hasText(clientToken)&&stringRedisTemplate.hasKey(clientToken)){
            chain.doFilter(request, response);
            return;
        }
        //校验失败,向前端响应失败的Result对象转成的json串
        BaseResponse res = ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR, "请登录");
        String jsonStr = JSONUtil.toJsonStr(res);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonStr);
        out.flush();
        out.close();
    }

    /**
     * 判断路径是否在白名单中
     */
    private boolean isWhiteListed(String path, List<String> whiteList) {
        for (String whitePath : whiteList) {
            if (pathMatcher.match(whitePath, path)) {
                return true;
            }
        }
        return false;
    }
}
