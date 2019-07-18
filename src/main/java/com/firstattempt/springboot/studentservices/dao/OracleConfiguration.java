package com.firstattempt.springboot.studentservices.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class OracleConfiguration {
	

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
	
	  @Value("${spring.datasource.hikari.maximum-pool-size}")
	    private int maximumPoolSize;
	  @Value("${spring.datasource.hikari.minimum-idle}")
	    private int minimumIdle;
	    @Value("${spring.datasource.hikari.connection-timeout}")
	    private int connectionTimeout;
	    @Value("${spring.datasource.hikari.idle-timeout}")
	    private int idleTimeout;
	 /* @Value("${spring.datasource.dbcp.validation-query}")
	    private String validationQuery;*/
	    @Value("${spring.datasource.dbcp.max-open-prepared-statements}")
	    private String cachePrepStmts;
	    @Value("${spring.datasource.pool-prepared-statements-cache-size}")
	    private String prepStmtCacheSize;
	    @Value("${spring.datasource.pool-prepared-statements-cache-sql-limit}")
	    private String prepStmtCacheSqlLimit;
	    @Value("${spring.datasource.user-server-prepared-statements}")
	    private String useServerPrepStmts;
	
	@Bean
    public DataSource primaryDataSource() {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(url); 
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setMaximumPoolSize(maximumPoolSize);
        hikariConfig.setMinimumIdle(minimumIdle);
        hikariConfig.setConnectionTimeout(connectionTimeout);
        hikariConfig.setIdleTimeout(idleTimeout);
      //  hikariConfig.setConnectionTestQuery(validationQuery);
        hikariConfig.setPoolName("springHikariCP");
        hikariConfig.setConnectionTestQuery("select * from STUDENT");
       hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", cachePrepStmts);
        hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSize", prepStmtCacheSize);
       hikariConfig.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", prepStmtCacheSqlLimit);
        hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", useServerPrepStmts);
        
        System.out.println(hikariConfig.toString());
        HikariDataSource ds = new HikariDataSource(hikariConfig);
        return ds;
	
	}

}
