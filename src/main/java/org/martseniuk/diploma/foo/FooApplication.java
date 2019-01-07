package org.martseniuk.diploma.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Spring boot application class.
 *
 * @author Roman_Martseniuk
 */
@EnableFeignClients
@SpringBootApplication
public class FooApplication {

    public static void main(String[] args) {
        SpringApplication.run(FooApplication.class, args);
    }
}
