package com.netcracker.project;

import com.netcracker.project.config.AppProperties;
import com.netcracker.project.config.FileStorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class, AppProperties.class
})
public class Application {

    /**
     * Entry point into the application
     * @param args - command-line arguments
     */
    public static void main(String[] args) {
        log.info("the app is running");
        SpringApplication.run(Application.class, args);
    }
}
