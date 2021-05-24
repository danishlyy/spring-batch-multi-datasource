package com.batch.example.demo.write;

import com.batch.example.demo.pojo.FundProduct;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-05-24 09:55
 */
@Configuration
@Slf4j
public class FundProductWriter {

    @Autowired
    @Qualifier("primaryDataSource")
    private DataSource primaryDataSource;

    @Bean
    public JdbcBatchItemWriter<FundProduct> writeProduct() {
        JdbcBatchItemWriter<FundProduct> batchItemWriter = new JdbcBatchItemWriter<>();
        batchItemWriter.setDataSource(primaryDataSource);
        batchItemWriter.setSql(" INSERT INTO product (id, product_code, product_name, product_type, "
                + "fund_manager_code, fund_trustee_code) VALUES (:id, :productCode, :productName, "
                + ":productType, :fundManagerCode, :fundTrusteeCode) ");
        batchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return batchItemWriter;
    }
}
