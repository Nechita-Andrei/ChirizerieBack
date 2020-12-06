package com.findork.chiriezerie.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public Gson getGson(){
        return new Gson();
    }
}
