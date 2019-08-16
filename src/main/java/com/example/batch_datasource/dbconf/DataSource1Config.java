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
		basePackages = "com.example.batch_datasource.repository.testdb1",
		entityManagerFactoryRef = "entityManagerFactory1",
		transactionManagerRef = "transactionManager1"
		)
@EnableTransactionManagement
public class DataSource1Config {
	@Autowired
    private JpaProperties jpaProperties;
	
	//创建test1数据库的数据源
	@Bean(name = "test1DataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.test1")
	public DataSource testDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	// EntityManagerFactory配置
	@Bean(name = "entityManagerFactory1")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory1(@Qualifier("test1DataSource") DataSource dataSource, 
			EntityManagerFactoryBuilder  builder)
			throws Exception {
		return builder
				.dataSource(dataSource)
				.properties(jpaProperties.getProperties())
				.packages("com.example.batch_datasource.entity.db1")//设置实体类所在位置
				.persistenceUnit("db1PersistenceUnit")
				.build();
	}

	//主数据源事务配置
	@Bean(name = "transactionManager1")
    @Primary
    public PlatformTransactionManager transactionManager1(@Qualifier("entityManagerFactory1") LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager(primaryEntityManagerFactory.getObject());
        return transactionManager;
    }

}
