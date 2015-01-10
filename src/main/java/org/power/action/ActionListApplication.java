package org.power.action;

import javax.sql.DataSource;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@ComponentScan
@EnableJpaRepositories
@Import(RepositoryRestMvcConfiguration.class)
@EnableAutoConfiguration
public class ActionListApplication {

  @Autowired
  private Environment environment;

  public static void main(String[] args) {
    SpringApplication.run(ActionListApplication.class, args);
  }

  /**
   * Bean: Data source
   *
   * @return dataSource bean
   */
  @Bean(name = "dataSource", destroyMethod = "close")
  @Profile("mopass")
  public DataSource oscDataSource() {
    String dbUrl =
        "jdbc:mysql://10.4.26.93:3306/" + getProperty("db_name") + "?useUnicode=true&characterEncoding=UTF-8";
    DruidDataSource dataSource = new DruidDataSource();

    // Basic attribute
    dataSource.setUrl(dbUrl);
    dataSource.setUsername(getProperty("db_username"));
    dataSource.setPassword(getProperty("db_password"));

    // Pool size
    dataSource.setInitialSize(8);
    dataSource.setMinIdle(8);
    dataSource.setMaxActive(128);

    // Connection wait time limit
    dataSource.setMaxWait(DateUtils.MILLIS_PER_MINUTE);

    // Time between connection check
    dataSource.setTimeBetweenEvictionRunsMillis(DateUtils.MILLIS_PER_MINUTE);

    // Time of connection max live time
    dataSource.setMinEvictableIdleTimeMillis(5 * DateUtils.MILLIS_PER_MINUTE);

    // Connection test
    dataSource.setValidationQuery("SELECT 'x'");
    dataSource.setTestWhileIdle(true);
    dataSource.setTestOnBorrow(false);
    dataSource.setTestOnReturn(false);

    // PS cache
    dataSource.setPoolPreparedStatements(true);
    dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

    return dataSource;
  }

  /**
   * Bean: Data source
   *
   * @return dataSource bean
   */
  @Bean(name = "dataSource", destroyMethod = "close")
  @Profile("jae")
  public DataSource jaeDataSource() {
    DruidDataSource dataSource = new DruidDataSource();

    // Basic attribute
    dataSource.setUrl(getProperty("db.url"));
    dataSource.setUsername(getProperty("db.username"));
    dataSource.setPassword(getProperty("db.password"));

    // Pool size
    dataSource.setInitialSize(8);
    dataSource.setMinIdle(8);
    dataSource.setMaxActive(128);

    // Connection wait time limit
    dataSource.setMaxWait(DateUtils.MILLIS_PER_MINUTE);

    // Time between connection check
    dataSource.setTimeBetweenEvictionRunsMillis(DateUtils.MILLIS_PER_MINUTE);

    // Time of connection max live time
    dataSource.setMinEvictableIdleTimeMillis(5 * DateUtils.MILLIS_PER_MINUTE);

    // Connection test
    dataSource.setValidationQuery("SELECT 'x'");
    dataSource.setTestWhileIdle(true);
    dataSource.setTestOnBorrow(false);
    dataSource.setTestOnReturn(false);

    // PS cache
    dataSource.setPoolPreparedStatements(true);
    dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

    return dataSource;
  }

  /**
   * Simply get property in PropertySource
   *
   * @param name property name
   * @return property value
   */
  private String getProperty(final String name) {
    return environment.getProperty(name);
  }

}
