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
 * mysql 网校（用户/员工）
 * @author yyz
 */
@Configuration
@ConditionalOnClass(HikariDataSource.class)
@MapperScan(basePackages = "com.marketing.activity.mapper.mysql.wangxiao",
        sqlSessionTemplateRef = "mysqlWangXiaoSqlSessionTemplate")
public class MySqlWangXiaoDataSourceConfig {

    private static final String MAPPER_LOCATION = "classpath:mapper/mysql/wangxiao/*.xml";

    @Bean(name = "mysqlWangXiaoDatasource")
    @ConfigurationProperties(prefix = "app.datasource.mysql.wangxiao")
    public DataSource mysqlWangXiaoDataSource(){
        return new HikariDataSource();
    }

    @Bean(name = "mysqlWangXiaoSqlSessionFactory")
    public SqlSessionFactory mysqlWangXiaoSqlSessionFactory(@Qualifier("mysqlWangXiaoDatasource") DataSource dataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "mysqlWangXiaoTransactionManager")
    public DataSourceTransactionManager mysqlWangXiaoTransactionManager(@Qualifier("mysqlWangXiaoDatasource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "mysqlWangXiaoSqlSessionTemplate")
    public SqlSessionTemplate mysqlWangXiaoSqlSessionTemplate(@Qualifier("mysqlWangXiaoSqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
