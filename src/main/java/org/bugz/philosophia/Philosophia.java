package org.bugz.philosophia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring bootstrap main configuration.
 *
 * @author bugz
 */
@Configuration
@ComponentScan(basePackages = "org.bugz.philosophia")
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories("org.bugz.philosophia")
@PropertySource(value = {"classpath:philosophia.properties"}, ignoreResourceNotFound = true)
public class Philosophia {

    public static void main(String[] args) {
        SpringApplication.run(Philosophia.class, args);
    }
    
}
