package com.tuan.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tắt CSRF nếu là API
                .formLogin(form -> form.disable()) // Tắt form login mặc định
                .httpBasic(httpBasic -> httpBasic.disable()) // Tắt basic auth nếu không dùng
                .cors(cors -> cors.disable()) // Nếu bạn muốn disable CORS hoàn toàn
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Cho phép mọi request (hoặc bạn có thể cấu hình cụ thể)
                );
        return http.build();
    }

}
