package com.example.projectagile.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Bật CSRF để bảo vệ form
        http.csrf(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize
                            .requestMatchers("/login", "/register").permitAll() // Cho phép truy cập login và register
                            .requestMatchers("/").authenticated() // Yêu cầu đăng nhập để truy cập trang chủ
                            .anyRequest().authenticated(); // Các request khác cần đăng nhập
                })
                .formLogin(form -> form
                        .loginPage("/login") // Trang login tùy chỉnh
                        .defaultSuccessUrl("/", true) // Sau khi đăng nhập thành công sẽ chuyển đến trang chính
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL để logout
                        .logoutSuccessUrl("/login") // Chuyển hướng sau khi logout
                        .permitAll()
                );
        return http.build();
    }
}
