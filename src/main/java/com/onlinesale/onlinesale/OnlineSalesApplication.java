package com.onlinesale.onlinesale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.hateoas.HypermediaAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableAutoConfiguration(exclude = HypermediaAutoConfiguration.class)
public class OnlineSalesApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineSalesApplication.class, args);
    }

}
