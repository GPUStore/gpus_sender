package ru.mephi.gpus_sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GpusSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpusSenderApplication.class, args);
    }

}
