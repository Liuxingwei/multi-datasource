package cn.liuxingwei.multidatasource.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "cn.liuxingwei.multidatasource.mapper.sqlite.slave", sqlSessionTemplateRef = "sqliteSlaveSessionTemplate")
public class SqliteSlave {
    @Bean
    @ConfigurationProperties(prefix = "sqlite.slave")
    @Qualifier("sqliteSlaveDataSource")
    public DataSource sqliteSlaveDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean
    @Qualifier("sqliteSlaveSessionFactory")
    public SqlSessionFactory sqliteSlaveSessionFactory(@Qualifier("sqliteSlaveDataSource") DataSource sqliteSlaveDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(sqliteSlaveDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean
    @Qualifier("sqliteSlaveTransactionManager")
    public DataSourceTransactionManager sqliteSlaveTransactionManager(@Qualifier("sqliteSlaveDataSource") DataSource sqliteSlaveDataSource) {
        return new DataSourceTransactionManager(sqliteSlaveDataSource);
    }

    @Bean
    @Qualifier("sqliteSlaveSessionTemplate")
    public SqlSessionTemplate sqliteSlaveSessionTemplate(@Qualifier("sqliteSlaveSessionFactory") SqlSessionFactory sqliteSlaveSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqliteSlaveSessionFactory);
    }
}

