package com.batch.example.demo.processor;

import com.batch.example.demo.pojo.FundProduct;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-05-24 09:54
 */
@Component
@Slf4j
public class FundProductProcessor implements ItemProcessor<FundProduct, FundProduct> {

    @Override
    public FundProduct process(FundProduct fundProduct) throws Exception {
        FundProduct product = new FundProduct();
        // 基金代码标准化
        product.setId(UUID.randomUUID().toString().replace("-", ""));
        product.setProductCode("standard" + fundProduct.getProductCode());
        product.setFundTrusteeCode(fundProduct.getFundTrusteeCode());
        product.setFundManagerCode(fundProduct.getFundManagerCode());
        product.setProductType(fundProduct.getProductType());
        product.setProductName(fundProduct.getProductName());
        return product;
    }
}
