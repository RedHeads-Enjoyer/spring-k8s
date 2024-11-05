package com.example.demo;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.example.demo") // Убедитесь, что здесь указан правильный пакет
public class FeignClientConfig {}
