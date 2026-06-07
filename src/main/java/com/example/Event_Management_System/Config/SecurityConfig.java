package com.example.Event_Management_System.Config;

import com.example.Event_Management_System.Security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> {})
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/tickets/**").permitAll()
//                        .requestMatchers("/api/events/**").permitAll()
//                        .requestMatchers("/api/users/**").permitAll()
//                        .requestMatchers("/api/payments/**").permitAll()
//                        .anyRequest().permitAll()   // TEMP: allow everything
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    //    @Bean
////    public PasswordEncoder encoder() {
////        return new BCryptPasswordEncoder();
////    }
////
////    @Bean
////    public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
////        return config.getAuthenticationManager();
////    }
////
////
//    @Bean
//    public WebMvcConfigurer cors() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedOrigins("*")
//                        .allowedMethods("*")
//                        .allowedHeaders("*");
//            }
//        };
//    }
//}


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter JwtAuthFilter;

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//                .cors(Customizer.withDefaults())   // 🔑 THIS LINE IS CRITICAL
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/users/login").permitAll()
//                        .requestMatchers("/api/events/**").permitAll()
//                        .requestMatchers("/api/tickets/**").authenticated()
//                        .requestMatchers("/api/users").permitAll() // registration if needed
//                        .anyRequest().authenticated()
//                );
//
//        return http.build();
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(sess ->
//                        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/users/login").permitAll()
//                        .requestMatchers("/api/events/**").permitAll()
//                        .requestMatchers("/api/tickets/**").authenticated()
//                        .requestMatchers("/api/users").permitAll()
//                        .anyRequest().permitAll()
//                )
//                .addFilterBefore(JwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(sess ->
                        sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/login", "/api/users/register").permitAll()
                        .requestMatchers("/api/users/me").authenticated()
                        .requestMatchers("/api/users/**").hasRole("Admin")
                        .requestMatchers(HttpMethod.GET, "/api/events/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/events/**").hasAnyRole("Admin","Organizer")
                        .requestMatchers(HttpMethod.PUT, "/api/events/**").hasAnyRole("Admin","Organizer")
                        .requestMatchers(HttpMethod.DELETE, "/api/events/**").hasAnyRole("Admin","Organizer")
                        .requestMatchers("/api/tickets/create").authenticated()
                        .requestMatchers("/api/tickets/user/**").authenticated()
                        .requestMatchers("/api/tickets/event/**").hasAnyRole("Admin","Organizer")
                        .requestMatchers("/api/tickets/**").hasAnyRole("Organizer","Admin")
                        .requestMatchers(HttpMethod.POST, "/api/payments/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/payments/**").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/payments/**").hasAnyRole("Admin","Organizer")
                        .requestMatchers(HttpMethod.DELETE, "/api/payments/**").hasAnyRole("Admin","Organizer")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(JwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
