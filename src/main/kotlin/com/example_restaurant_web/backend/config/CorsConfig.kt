package com.example_restaurant_web.backend.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {

        registry.addMapping("/**")
            .allowedOrigins(
                "http://127.0.0.1:5500",
                "http://localhost:5500",
                "https://fifoag1-cloud.github.io"
            )
            .allowedMethods(
                "GET",
                "POST",
                "PUT",
                "DELETE"
            )
    }
}