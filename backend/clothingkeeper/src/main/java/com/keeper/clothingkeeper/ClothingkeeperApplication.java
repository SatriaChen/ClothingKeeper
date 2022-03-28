package com.keeper.clothingkeeper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@MapperScan("com.keeper.clothingkeeper.dao")
public class ClothingkeeperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothingkeeperApplication.class, args);
    }

}
