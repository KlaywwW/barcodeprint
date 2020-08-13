package com.starvincci.barcodeprint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.starvincci.barcodeprint.config", "com.starvincci.barcodeprint.read", "com.starvincci.barcodeprint.write", "com.starvincci.barcodeprint.controller"})
public class BarcodeprintApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BarcodeprintApplication.class, args);
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }

}