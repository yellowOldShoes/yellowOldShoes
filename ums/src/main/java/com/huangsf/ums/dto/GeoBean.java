package com.huangsf.ums.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangsf
 * @create 2025-01-17  14:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoBean {

    private String country;
    private String area;
    private String province;
    private String city;
    private String isp;

}
