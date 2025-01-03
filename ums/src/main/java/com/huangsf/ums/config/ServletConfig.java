package com.huangsf.ums.config;

import com.huangsf.ums.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author huangsf
 * @create 2024-12-09  15:04
 */
@Configuration
public class ServletConfig {

    //注入redis模板
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WhiteListConfig whiteListConfig;

    /**
     * 注册原生Servlet的Filter
     */
    @Bean
    public FilterRegistrationBean securityFilter(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        //创建SecurityFilter对象
        TokenFilter securityFilter = new TokenFilter();
        //给SecurityFilter对象注入redis模板
        securityFilter.setStringRedisTemplate(redisTemplate);

        securityFilter.setWhiteListConfig(whiteListConfig);
        //注册SecurityFilter
        filterRegistrationBean.setFilter(securityFilter);
        //配置SecurityFilter拦截所有请求
        filterRegistrationBean.addUrlPatterns("/user/*","/menu/*");

        return filterRegistrationBean;
    }
}
