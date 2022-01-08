package com.example.springproj10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;

@SpringBootApplication
public class Springproj10Application implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Springproj10Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(Arrays.toString(args));
//		String sql = "INSERT INTO students (name, email) VALUES ("
//				+ "'Nam Ha Minh', 'nam@codejava.net')";
//
//		int rows = jdbcTemplate.update(sql);
//		if (rows > 0) {
//			System.out.println("A new row has been inserted.");
//		}
    }


}