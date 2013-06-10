package com.zhentao.embedded.mysql.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zhentao.embedded.mysql.dao.TagDao;
import com.zhentao.embedded.mysql.dao.TagDaoJdbcImpl;
import com.zhentao.embedded.mysql.dao.TagScanDao;
import com.zhentao.embedded.mysql.dao.TagScanDaoJdbcImpl;

@Configuration
@PropertySource(value = { "file:${PROPERTIES_FILE}" })
@EnableTransactionManagement
@Import(StandaloneDataSourceConfig.class)
public class SpringConfig {

    @Autowired
    DataSourceConfig dataSourceConfig;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        // search local properties last by default
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSourceConfig.dataSource());
    }


    @Bean
    public TagScanDao tagScanDao() {
        TagScanDaoJdbcImpl dao = new TagScanDaoJdbcImpl();
        dao.setDataSource(dataSourceConfig.dataSource());
        return dao;
    }

    @Bean
    public TagDao tagDao() {
        TagDaoJdbcImpl dao = new TagDaoJdbcImpl();
        dao.setDataSource(dataSourceConfig.dataSource());
        dao.setJdbcInsert(new SimpleJdbcInsert(dataSourceConfig.dataSource()).withTableName("tag")
                                        .usingGeneratedKeyColumns("id"));
        return dao;
    }
}
