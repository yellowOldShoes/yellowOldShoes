package com.huangsf.ums.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author huangsf
 * @create 2024-12-09  16:23
 */
@Component
@ConfigurationProperties(prefix = "white-list")
public class WhiteListConfig {
    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
