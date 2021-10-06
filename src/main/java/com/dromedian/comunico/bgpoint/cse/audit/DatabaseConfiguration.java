/*package com.dromedian.comunico.bgpoint.cse.audit;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@EnableJpaRepositories(entityManagerFactoryRef = "mysqlEntityManagerFactory",
transactionManagerRef = "mysqlTransactionManager", basePackages = {"com.dromedian.comunico.bgpoint.cse.repository"})
@ComponentScan(basePackages = {"com.dromedian.comunico.bgpoint.cse"})
@EntityScan(basePackages = {"com.dromedian.comunico.bgpoint.cse.entity"})
@EnableJpaAuditing(auditorAwareRef = "auditorAware")

public class DatabaseConfiguration {

	@Bean
	public DataSource dataSource() {

		Properties properties = new Properties();
		properties.setProperty("spring.datasource.initial-size","10");
		properties.setProperty("spring.datasource.max-active","40");

		//properties.setProperty("spring.datasource.max-wait", this.springDataSourceProperties.getMaxWait());

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/bgpoint_cse?useSSL=false&allowPublicKeyRetrieval=true");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setConnectionProperties(properties);
		return dataSource;
	}

	Properties jpaProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}


	@Bean(name = "mysqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
				new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan(new String[] {"com.dromedian.comunico.bgpoint.cse.entity"});

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(false);
		vendorAdapter.setGenerateDdl(true);
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(jpaProperties());

		return entityManagerFactoryBean;
	}


	@Bean("mysqlTransactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);

		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new CSEAuditorAwareImpl();
	}		
}
*/