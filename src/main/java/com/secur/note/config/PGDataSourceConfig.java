package com.secur.note.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.secur.note.pgsql",
    entityManagerFactoryRef = "employeeEntityManagerFactory",
    transactionManagerRef = "employeeTransactionManager"
)
public class PGDataSourceConfig {

    @Bean(name = "employeeDataSource")
    public DataSource employeeDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:postgresql://localhost:5432/employeedb")
                .username("postgres")
                .password("Password")
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean(name = "employeeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("employeeDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.secur.note.pgsql")
                .persistenceUnit("employee")
                .build();
    }

    @Bean(name = "employeeTransactionManager")
    public PlatformTransactionManager employeeTransactionManager(
            @Qualifier("employeeEntityManagerFactory") EntityManagerFactory employeeEntityManagerFactory) {
        return new JpaTransactionManager(employeeEntityManagerFactory);
    }
}

