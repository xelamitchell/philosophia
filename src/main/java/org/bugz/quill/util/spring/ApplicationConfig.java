package org.bugz.quill.util.spring;

import org.bugz.quill.telnet.TelnetServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;

/**
 * Application-wide configurations.
 * 
 * @author bugz
 */
@Configuration
public class ApplicationConfig {
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    public PersistenceAnnotationBeanPostProcessor persistenceAnnotationBeanPostProcessor() {
        return new PersistenceAnnotationBeanPostProcessor();
    }
    
    @Bean
    public TelnetServer telnet() {
        return new TelnetServer();
    }
    
}
