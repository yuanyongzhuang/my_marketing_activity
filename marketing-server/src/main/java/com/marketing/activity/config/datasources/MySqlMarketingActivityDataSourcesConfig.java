package com.marketing.activity.config.datasources;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 *  mysql 营销活动（券）
 * @author yyz
 */
@Configuration
@ConditionalOnClass(HikariDataSource.class)
@MapperScan(basePackages = "com.marketing.activity.mapper.mysql.marketingActivity",
        sqlSessionTemplateRef = "mysqlMarketingActivitySqlSessionTemplate")
public class MySqlMarketingActivityDataSourcesConfig {

    private static final String MAPPER_LOCATION = "classpath:mapper/mysql/marketingActivity/*.xml";

    /**
     * prefix 不能使用驼峰命名规则
     * @return
     */
    @Primary
    @Bean(name = "mysqlMarketingActivityDatasource")
    @ConfigurationProperties(prefix = "app.datasource.mysql.marketing-activity")
    public DataSource mysqlMarketingActivityDataSource(){
        return new HikariDataSource();
    }

    @Primary
    @Bean(name = "mysqlMarketingActivitySqlSessionFactory")
    public SqlSessionFactory mysqlMarketingActivitySqlsessionFactory(@Qualifier("mysqlMarketingActivityDatasource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "mysqlMarkeingActivityTransactionManager")
    public DataSourceTransactionManager mySqlMarketingActivityTransactionManager(@Qualifier("mysqlMarketingActivityDatasource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = "mysqlMarketingActivitySqlSessionTemplate")
    public SqlSessionTemplate mysqlMarketingActivitySqlSessionTemplate(@Qualifier("mysqlMarketingActivitySqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
