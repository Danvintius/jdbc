package ru.netology.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static ru.netology.jdbc.Repository.read;

@SpringBootApplication
public class Runner implements CommandLineRunner {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Runner.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws SQLException {
        //Connection connection = dataSource.getConnection();
        //Statement statement = connection.createStatement();
        String script = read("data.sql");
        List<Product> products = jdbcTemplate.query(script, (resultSet, rowNum) -> {
            String name = resultSet.getString("name");
            return new Product(name);

        });
        products.forEach(System.out::println);
    }
}