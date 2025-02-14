package com.huangsf.ums.util;

import com.huangsf.ums.dto.GeoBean;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author huangsf
 * @create 2025-01-16  17:04
 */
public class GeoIpService {

    public static GeoBean getLocationByIP(String ip) throws Exception {
        Searcher searcher = null;
        String region=null;
        GeoBean geoBean = new GeoBean();
        String dbPath = "D:\\yupi_code\\auth-front\\ERP-Center\\ums\\src\\main\\resources\\ip2region.xdb";
        try {

            searcher = Searcher.newWithFileOnly(dbPath);
        } catch (IOException e) {
            System.out.printf("failed to create searcher with `%s`: %s\n", dbPath, e);
            return geoBean;
        }

//        String ip = "61.174.158.89";
        // 2、查询
        try {

            long sTime = System.nanoTime();
            region = searcher.search(ip);
            long cost = TimeUnit.NANOSECONDS.toMicros((long) (System.nanoTime() - sTime));

            if(StringUtils.isNotBlank(region)){
                String[] split = region.split("|");
                geoBean.setCountry(split[0]);
                geoBean.setArea(split[1]);
                geoBean.setProvince(split[2]);
                geoBean.setCity(split[3]);
                geoBean.setIsp(split[4]);
            }
        } catch (Exception e) {
            System.out.printf("failed to search(%s): %s\n", ip, e);
        }

        // 3、关闭资源
        if(searcher!=null){
            searcher.close();
        }
        return geoBean;
        // 备注：并发使用，每个线程需要创建一个独立的 searcher 对象单独使用。
    }
}
