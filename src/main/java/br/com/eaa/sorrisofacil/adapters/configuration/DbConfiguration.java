package br.com.eaa.sorrisofacil.adapters.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DbConfiguration {
    @Value("${db.config.username}")
    private String username;

    @Value("${db.config.password}")
    private String password;

    @Value("${db.config.url}")
    private String url;

    @Bean
    public DataSource getDataSource(){
        return DataSourceBuilder.create()
                .username(username)
                .password(password)
                .driverClassName("org.postgresql.Driver")
                .url(url)
                .build();
    }
}
