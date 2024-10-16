package com.ems.sow.config;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "mysqlEntityManagerFactory",
//        basePackages = "com.ems.sow.repositories",
//        transactionManagerRef = "mysqlTransactionManager"
//)
/**
 * This class is used to configure the MySQL database connection and entity manager factory.
 */
public class MySqlConfig {

//    @Primary
//    @Bean(name = "mysqlDataSource")
//    @ConfigurationProperties(prefix = "spring.mysql.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = "mysqlEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder entityManagerFactoryBuilder,
//                                                                           @Qualifier("mysqlDataSource") DataSource dataSource) {
//        HashMap<String, Object> properties = new HashMap<>();
//        properties.put("hibernate.hbm2ddl.auto", "update");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
//
//        return entityManagerFactoryBuilder
//                .withDataSource(dataSource)
//                .properties(properties)
//                .packages("com.ems.sow.models")
//                .persistenceUnit("db")
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "mysqlTransactionManager")
//    public PlatformTransactionManager transactionManager(@Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
}
