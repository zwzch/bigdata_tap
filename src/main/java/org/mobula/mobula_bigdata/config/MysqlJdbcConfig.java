/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package org.mobula.mobula_bigdata.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class MysqlJdbcConfig {

    @Autowired
    Environment env;

    @Bean(name = "mysqlJdbcDataSource")
    @Qualifier("mysqlJdbcDataSource")
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setUrl(env.getProperty("mysql.url"));
        dataSource.setDriverClassName(env.getProperty("mysql.driver-class-name"));
        dataSource.setUsername(env.getProperty("mysql.user"));
        dataSource.setPassword(env.getProperty("mysql.password"));
//        logger.debug("Hive DataSource Inject Successfully...");
        return dataSource;
    }
}
