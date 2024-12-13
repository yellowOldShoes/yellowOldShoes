package com.huangsf.ums;

import com.huangsf.ums.config.WhiteListConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@SpringBootTest
class UmsApplicationTests {

    @Test
    void contextLoads() {
//        // 获取当前时间
//        LocalDateTime now = LocalDateTime.now();
//
//        // 格式化当前时间以便显示
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        System.out.println("当前时间: " + now.format(formatter));
//
//        // 在当前时间上加上两个月
//        LocalDateTime twoMonthsLater = now.plusMonths(2);
//
//        // 格式化并显示两个月后的时间
//        System.out.println("两个月后的时间: " + twoMonthsLater.format(formatter));

        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        zoneIds.forEach(System.out::println);  // 输出所有可用的时区

    }


    @Autowired
    private WhiteListConfig whiteListConfig;

    @Test
    public void printWhiteList() {
        System.out.println("White List: " + whiteListConfig.getUrls());
    }
}
