package com.example.interview_scheduler.config;


import com.example.interview_scheduler.serviceImpl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/register","/auth/login").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/hr/**").hasRole("HR")
                        .requestMatchers("/interviewer/**").hasRole("INTERVIEWER")
                        .requestMatchers("/candidate/**").hasRole("CANDIDATE"). anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }




    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtUtil, customUserDetailsService);
    }
}
