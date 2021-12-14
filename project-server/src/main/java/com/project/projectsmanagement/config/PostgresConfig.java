package com.project.projectsmanagement.config;


import com.project.projectsmanagement.converters.LecturerReadConverter;
import com.project.projectsmanagement.converters.StudentReadConverter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.reactive.TransactionalOperator;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableR2dbcRepositories
@EnableTransactionManagement
public class PostgresConfig extends AbstractR2dbcConfiguration {

    @Bean("myConnectionFactory")
    @Override
    public ConnectionFactory connectionFactory() { //konfiguracja w pliku application.properties
        return new PostgresqlConnectionFactory(
                PostgresqlConnectionConfiguration.builder()
                        .host("localhost")
                        .port(5432)
                        .username("postgres")
                        .password("postgres")
                        .database("projekty")
                        .build());
    }

    @Bean
    ReactiveTransactionManager reactiveTransactionManager(@Qualifier("myConnectionFactory") ConnectionFactory connectionFactory) {
        return new R2dbcTransactionManager(connectionFactory);
    }

    @Bean
    TransactionalOperator transactionalOperator(ReactiveTransactionManager reactiveTransactionManager) {
        return TransactionalOperator.create(reactiveTransactionManager);
    }

    @Override
    protected List<Object> getCustomConverters() {

        List<Object> list = new ArrayList<>();

        list.add(new LecturerReadConverter());
        list.add(new StudentReadConverter());

       return list;
    }
}
