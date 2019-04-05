package cn.liuxingwei.multidatasource.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "cn.liuxingwei.multidatasource.mapper.sqlite.master")
public class SqliteMaster {
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "sqlite.master")
    public DataSource sqliteMasterDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public SqlSessionFactory sqliteMasterSessionFactory(DataSource sqliteMasterDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(sqliteMasterDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager sqliteMasterTransactionManager(DataSource sqliteMasterDataSource) {
        return new DataSourceTransactionManager(sqliteMasterDataSource);
    }

    @Bean
    @Primary
    public SqlSessionTemplate sqliteMasterSessionTemplate(SqlSessionFactory sqliteMasterSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqliteMasterSessionFactory);
    }
}
