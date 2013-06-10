package com.zhentao.embedded.mysql.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmbeddedDataSourceConfig implements DataSourceConfig {

    @Override
    @Bean(destroyMethod="shutdown")
    public DataSource dataSource() {
        return new EmbeddedMysqlDatabaseBuilder().addSqlScript("tag_schema.sql").addSqlScript("tag_init.sql").build();
    }
}
