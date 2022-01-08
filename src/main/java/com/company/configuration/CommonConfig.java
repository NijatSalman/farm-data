package com.company.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Configuration
public class CommonConfig {

    @Bean
    public DateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }

}
