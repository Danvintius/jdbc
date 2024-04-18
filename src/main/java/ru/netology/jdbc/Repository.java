package ru.netology.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class Repository {
    @Autowired
    private static DataSource dataSource;

    @Autowired
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static String script = read("data.sql");

    public static void main(String[] args) throws SQLException {
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        List<Product> products = jdbcTemplate.query(script, (resultSet, rowNum) -> {
            String name = resultSet.getString("name");
            return new Product(name);

        });
        products.forEach(System.out::println);
    }

    public static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    List<String> getProductName(String name) {
        return namedParameterJdbcTemplate.queryForList(script, Map.of("name", name), String.class);
    }
}
