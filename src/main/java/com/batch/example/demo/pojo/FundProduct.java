package com.batch.example.demo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-05-24 09:51
 */
@Getter
@Setter
@ToString
public class FundProduct {


    private String id;

    /**
     * 基金代码
     */
    private String productCode;

    /**
     * 基金简称
     */
    private String productName;
    /**
     * 基金类型
     */
    private String productType;
    /**
     * 基金管理人代码
     */
    private String fundManagerCode;
    /**
     * 基金托管人代码
     */
    private String fundTrusteeCode;


}
