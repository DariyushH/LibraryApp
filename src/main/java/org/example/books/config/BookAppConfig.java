package org.example.books.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "org.example.books")
@EnableJpaRepositories("org.example.books.repository")
@EnableAspectJAutoProxy
@EnableWebMvc
public class BookAppConfig {
}

