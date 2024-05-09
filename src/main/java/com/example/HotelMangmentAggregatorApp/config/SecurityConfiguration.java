package com.example.HotelMangmentAggregatorApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.HotelMangmentAggregatorApp.entity.Role;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {
        private final AuthenticationProvider authenticationProvider;
        private final JwtAuthenticationFilter jwtAuthFilter;

        public SecurityConfiguration(AuthenticationProvider authenticationProvider,
                        JwtAuthenticationFilter jwtAuthFilter) {
                this.authenticationProvider = authenticationProvider;
                this.jwtAuthFilter = jwtAuthFilter;
        }

        private static final String[] WHITE_LIST_URL = { "/auth/**",
                        "/hotels",
                        "/hotels/{hotelId},",
                        "/v2/api-docs",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html" };

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

                httpSecurity
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URL)
                                                .permitAll()
                                                .requestMatchers("/hotels/{hotelId}")
                                                .permitAll()
                                                .requestMatchers("/user/**")
                                                .hasAnyAuthority(Role.ADMIN.name(), Role.CUSTOMER.name())
                                                .requestMatchers("/admin/**")
                                                .hasAuthority(Role.ADMIN.name())
                                                .requestMatchers("/hotels/{hotelId}/**")
                                                .hasAnyAuthority(Role.ADMIN.name(), Role.CUSTOMER.name(),
                                                                Role.MANAGER.name())
                                                .anyRequest()
                                                .authenticated())
                                // do not store state validate with token
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                // invoke JWTAUth before authentication
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                return httpSecurity.build();
        }
}
