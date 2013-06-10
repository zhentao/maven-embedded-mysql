package com.zhentao.embedded.mysql.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StandaloneDataSourceConfig implements DataSourceConfig {

    @Value("${jdbc.driver.class.name}")
    private String jdbcDriverClassName;

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${jdbc.username}")
    private String jdbcUsername;

    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Value("${jdbc.max.active}")
    private int jdbcMaxActive;

    @Value("${jdbc.initial.size}")
    private int jdbcInitialSize;

    @Value("${jdbc.validation.query}")
    private String jdbcValidationQuery;

    @Value("${jdbc.validation.interval}")
    private long jdbcValidationInterval;

    @Override
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        PoolProperties p = new PoolProperties();
        p.setUrl(jdbcUrl);
        p.setDriverClassName(jdbcDriverClassName);
        p.setUsername(jdbcUsername);
        p.setPassword(jdbcPassword);

        p.setMaxActive(jdbcMaxActive);
        p.setInitialSize(jdbcInitialSize);
        p.setMaxIdle(jdbcMaxActive);
        p.setMinIdle(jdbcInitialSize);
        p.setValidationQuery(jdbcValidationQuery);
        p.setValidationInterval(jdbcValidationInterval);

        DataSource dataSource = new DataSource();
        dataSource.setPoolProperties(p);
        return dataSource;
    }
}
