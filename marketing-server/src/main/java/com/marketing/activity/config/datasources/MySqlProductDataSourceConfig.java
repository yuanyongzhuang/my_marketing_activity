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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * mysql 商品
 * @author yyz
 */
@Configuration
@ConditionalOnClass(HikariDataSource.class)
@MapperScan(basePackages = "com.marketing.activity.mapper.mysql.product",
        sqlSessionTemplateRef = "mysqlProductSqlSessionTemplate")
public class MySqlProductDataSourceConfig {

    private static final String MAPPER_LOCATION = "classpath:mapper/mysql/product/*.xml";

    @Bean(name = "mysqlProductDatasource")
    @ConfigurationProperties(prefix = "app.datasource.mysql.product")
    public DataSource mysqlProductDataSource(){
        return new HikariDataSource();
    }

    @Bean(name = "mysqlProductSqlSessionFactory")
    public SqlSessionFactory mysqlProductSqlSessionFactory(@Qualifier("mysqlProductDatasource") DataSource dataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mysqlProductTransactionManager")
    public DataSourceTransactionManager mysqlProductTransactionManager(@Qualifier("mysqlProductDatasource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlProductSqlSessionTemplate")
    public SqlSessionTemplate mysqlProductSqlSessionTemplate(@Qualifier("mysqlProductSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
