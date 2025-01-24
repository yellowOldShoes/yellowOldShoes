package com.huangsf.ums;

import com.huangsf.ums.config.WhiteListConfig;
import com.huangsf.ums.constant.SystemConstant;
import com.huangsf.ums.model.User;
import com.huangsf.ums.util.GeoIpService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class UmsApplicationTests {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Employ{
        private Integer age;
        private String name;
    }

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
//        System.out.println("White List: " + whiteListConfig.getUrls());
        Employ jack = new Employ(12, "jack");
        Employ rose = new Employ(15, "rose");
        ArrayList<Employ> employs = new ArrayList<>();
        employs.add(jack);
        employs.add(rose);
        System.out.println(employs);
        Collections.sort(employs, (e1,e2)->e2.age-e1.age);
        System.out.println(employs);
    }

    @Test
    public void testInterface(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("hello");
        strings.add("world");
        for(String str:strings){
            print(s-> System.out.println(s),str);
        }
    }

    public void print(TestInterface tf,String str){
        tf.print(str);
    }

    @Test
    public void testIp() throws Exception {
        GeoIpService.getLocationByIP("");
    }

    @Test
    public void testIp2() throws IOException {
        // 数据库文件路径
        String dbPath = "D:\\yupi_code\\auth-front\\ERP-Center\\ums\\src\\main\\resources\\ip2region.xdb";

        Searcher searcher = null;
        try {
            searcher = Searcher.newWithFileOnly(dbPath);
        } catch (IOException e) {
            System.out.printf("failed to create searcher with `%s`: %s\n", dbPath, e);
            return;
        }

        // 2、查询
        try {
            String ip = "61.174.158.89";
            long sTime = System.nanoTime();
            String region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));

            System.out.printf("{region: %s, ioCount: %d, took: %d μs}\n", region, searcher.getIOCount(), cost);
        } catch (Exception e) {
//            System.out.printf("failed to search(%s): %s\n", ip, e);
        }

        // 3、关闭资源
        if(searcher!=null){
            searcher.close();
        }

        // 备注：并发使用，每个线程需要创建一个独立的 searcher 对象单独使用。
    }

    @Test
    void cteateUser(){
        User user = new User();
        String password = "123456";
        password = DigestUtils.md5DigestAsHex((password+ SystemConstant.SALT).getBytes());
        user.setPassword(password);
        user.setName("刘协");
        user.setAccount("liuxie@qq.com");
    }

}

