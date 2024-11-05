package com.example.demo.configs;

import com.example.demo.models.User;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private final UserRepository userRepository;

    public SecurityConfig(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorize -> authorize
                        .requestMatchers("/", "/login/**", "/error", "/oauth2/**").permitAll()  // Страница входа и ошибки
                        .anyRequest().permitAll()  // Остальные запросы доступны без авторизации
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/")  // Кастомная страница входа
                        .defaultSuccessUrl("/info", true)  // Редирект на /info после успешного входа
                        .failureUrl("/")  // Редирект на /oauth2 при ошибке входа
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")  // Редирект на /oauth2 после выхода
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();

        return userRequest -> {
            OAuth2User oAuth2User = delegate.loadUser(userRequest);
            String name = oAuth2User.getAttribute("name");
            UUID uuid = UUID.randomUUID();

            // Проверяем, существует ли уже пользователь с таким именем
            Optional<User> optionalUser = userRepository.findById(name);
            User user;

            if (optionalUser.isPresent()) {
                user = optionalUser.get();
                user.setToken(uuid.toString()); // обновляем токен, если пользователь уже существует
            } else {
                // Создаем нового пользователя, если его нет в базе
                user = new User(name, uuid.toString());
            }

            // Сохраняем пользователя в базе данных
            return userRepository.save(user);
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));  // Укажите разрешенные источники
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);  // Разрешение на использование куки
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
