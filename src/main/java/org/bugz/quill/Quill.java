package org.bugz.quill;

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
@ComponentScan(basePackages = "org.bugz.quill")
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories("org.bugz.quill")
@PropertySource(value = {"classpath:quill.properties"}, ignoreResourceNotFound = true)
public class Quill {

    public static void main(String[] args) {
        SpringApplication.run(Quill.class, args);
    }
    
}
