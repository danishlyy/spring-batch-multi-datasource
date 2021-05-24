package com.batch.example.demo.job;


import com.batch.example.demo.pojo.FundProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-05-24 09:54
 */
@Configuration
@Slf4j
public class FundProductSyncJob {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ItemReader<FundProduct> readFundProduct;
    @Autowired
    private ItemWriter<FundProduct> writeProduct;
    @Autowired
    private ItemProcessor<FundProduct, FundProduct> productProcessor;

    @Bean
    public Job productSyncJob() {
        return jobBuilderFactory.get("productSyncJob")
                .start(readFundProductStep())
                .build();
    }

    @Bean
    public Step readFundProductStep() {
        return stepBuilderFactory.get("readFundProductStep")
                .<FundProduct, FundProduct>chunk(1000)
                .reader(readFundProduct)
                .processor(productProcessor)
                .writer(writeProduct)
                .build();
    }

}
