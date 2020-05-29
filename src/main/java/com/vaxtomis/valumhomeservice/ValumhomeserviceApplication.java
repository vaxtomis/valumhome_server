package com.vaxtomis.valumhomeservice;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
//@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = "com.vaxtomis.valumhomeservice.repository")
public class ValumhomeserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ValumhomeserviceApplication.class, args);
    }
}
