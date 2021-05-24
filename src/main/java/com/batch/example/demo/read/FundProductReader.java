package com.batch.example.demo.read;

import com.batch.example.demo.pojo.FundProduct;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.OraclePagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-05-24 09:55
 */
@Configuration
@Slf4j
public class FundProductReader {

    @Autowired
    @Qualifier("chDataSource")
    private DataSource chDataSource;

    @Autowired
    @Qualifier("chJdbcTemplate")
    private JdbcTemplate chJdbcTemplate;

    @Bean
    public JdbcPagingItemReader<FundProduct> readFundProduct() {
        JdbcPagingItemReader<FundProduct> itemReader = new JdbcPagingItemReader<>();
        itemReader.setDataSource(chDataSource);
        itemReader.setFetchSize(1000);
        itemReader.setRowMapper((rs, rowNum) -> {
            FundProduct product = new FundProduct();
            product.setProductCode(rs.getString(2));
            product.setProductName(rs.getString(3));
            product.setProductType(rs.getString(4));
            product.setFundManagerCode(rs.getString(5));
            product.setFundTrusteeCode(rs.getString(6));
            return product;
        });
        OraclePagingQueryProvider queryProvider = new OraclePagingQueryProvider();
        queryProvider.setSelectClause("select ID,FSYMBOL,FDSNAME,FDTYPE,KEEPERCODE,TRUSTEECODE");
        queryProvider.setFromClause(" from TQ_FD_BASICINFO  ");
        Map<String, Order> sortedMap = new HashMap<>();
        sortedMap.put("ID", Order.DESCENDING);
        queryProvider.setSortKeys(sortedMap);
        itemReader.setQueryProvider(queryProvider);
        return itemReader;
    }
}
