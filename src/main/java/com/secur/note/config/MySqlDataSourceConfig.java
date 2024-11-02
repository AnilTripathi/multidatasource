package com.secur.note.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.secur.note.myql",
    entityManagerFactoryRef = "studentEntityManagerFactory",
    transactionManagerRef = "studentTransactionManager"
)
public class MySqlDataSourceConfig {

    @Primary
    @Bean(name = "studentDataSource")
    public DataSource studentDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/studentdb?allowPublicKeyRetrieval=true&useSSL=false")
                .username("root")
                .password("Password")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Primary
    @Bean(name = "studentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean studentEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("studentDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.secur.note.myql")
                .persistenceUnit("student")
                .build();
    }

    @Primary
    @Bean(name = "studentTransactionManager")
    public PlatformTransactionManager studentTransactionManager(
            @Qualifier("studentEntityManagerFactory") EntityManagerFactory studentEntityManagerFactory) {
        return new JpaTransactionManager(studentEntityManagerFactory);
    }
}
