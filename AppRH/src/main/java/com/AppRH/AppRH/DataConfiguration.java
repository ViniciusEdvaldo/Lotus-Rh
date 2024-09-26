package com.AppRH.AppRH;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/apprh?useTimezone=true&serverTimezone=UTC");

        dataSource.setUsername("vinicius");
        dataSource.setPassword("vinicius2128");
        
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Falha ao conectar ao banco de dados: " + e.getMessage());
        }

        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }
    
 
    
}
