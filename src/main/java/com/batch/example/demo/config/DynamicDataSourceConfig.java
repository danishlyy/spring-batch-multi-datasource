package com.batch.example.demo.config;

import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 李勇勇
 * @version 1.0
 * @date 2021-05-24 09:06
 */
@Slf4j
@Configuration
public class DynamicDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.primary")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("chDataSourceProperties")
    @ConfigurationProperties("spring.datasource.ch")
    public DataSourceProperties chDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean
    @Primary
    public DataSource primaryDataSource(@Autowired DataSourceProperties props) {
        return props.initializeDataSourceBuilder().build();
    }


    @Bean("chDataSource")
    public DataSource chDataSource(@Autowired @Qualifier("chDataSourceProperties") DataSourceProperties props) {
        return props.initializeDataSourceBuilder().build();
    }

    @Bean
    @Primary
    public JdbcTemplate primaryJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "chJdbcTemplate")
    public JdbcTemplate chJdbcTemplate(@Qualifier("chDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
