package com.programtom.rx_bloc_cli_wrapper_web_app;

import com.programtom.rx_bloc_cli_wrapper_web_app.utils.SpringContext;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableAutoConfiguration(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
//@Theme(themeClass = Material.class)

@Theme(value = "rx_bloc_cli_wrapper_web_app")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) throws Exception {
//        ProcessBuilder pb = new ProcessBuilder("SET PATH=/mnt/flutter/bin:${PATH}");
//        pb.inheritIO();
//        pb.start().waitFor();
//
//        pb = new ProcessBuilder("apt", "update");
//        pb.inheritIO();
//        pb.start().waitFor();
//
//
//        pb = new ProcessBuilder("apt", "install", "ruby-full");
//        pb.inheritIO();
//        pb.start().waitFor();


        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        SpringContext.setContext(context);
//        SpringApplication.run(Application.class, args);
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean(name = "rx_bloc_cli_wrapper_web_app")
    public MessageSource rx_bloc_cli_wrapper_web_app() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("rx_bloc_cli_wrapper_web_app_messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
