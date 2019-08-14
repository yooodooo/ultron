package com.github.udoo.ultron.config.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 多数据源配置
 * 用于查询
 *
 * @author dong.yang
 */
@Configuration
@MapperScan(basePackages = "com.github.udoo.ultron.dao.mapper.query", sqlSessionTemplateRef = "querySqlSessionTemplate")
public class SecondaryDataSource {

    @Bean(name = "queryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.two")
    public DataSource queryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "querySqlSessionFactory")
    public SqlSessionFactory querySqlSessionFactory(@Qualifier("queryDataSource") DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setDialectType("mysql");

        sqlSessionFactoryBean.setPlugins(new Interceptor[]{paginationInterceptor});
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "queryTransactionManager")
    public DataSourceTransactionManager queryTransactionManager(@Qualifier("queryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "querySqlSessionTemplate")
    public SqlSessionTemplate querySqlSessionTemplate(@Qualifier("querySqlSessionFactory") SqlSessionFactory sessionFactory) {
        return new SqlSessionTemplate(sessionFactory);
    }
}
