package com.example.batch_datasource.dbconf;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.example.batch_datasource.repository.testdb2",// 数据层所在的包位置
		entityManagerFactoryRef = "entityManagerFactory2",
		transactionManagerRef = "transactionManager2"
		)
@EnableTransactionManagement
public class DataSource2Config {

	@Autowired
    private JpaProperties jpaProperties;
	
	//创建test1数据库的数据源
	@Bean(name = "test2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.test2")
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	// EntityManagerFactory配置
	@Bean(name = "entityManagerFactory2")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory1(@Qualifier("test2DataSource") DataSource dataSource, 
			EntityManagerFactoryBuilder  builder)
			throws Exception {
		return builder
				.dataSource(dataSource)
				.properties(jpaProperties.getProperties())
				.packages("com.example.batch_datasource.entity.db2")//设置实体类所在位置
				.persistenceUnit("db2PersistenceUnit")
				.build();
	}

	//主数据源事务配置
	@Bean(name = "transactionManager2")
    public PlatformTransactionManager TransactionManager2(@Qualifier("entityManagerFactory2") LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(primaryEntityManagerFactory.getObject());
        return transactionManager;
    }
}
