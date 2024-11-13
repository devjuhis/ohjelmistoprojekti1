package project.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

import project.app.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig implements WebMvcConfigurer {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserService userService;

    private static final AntPathRequestMatcher[] WHITE_LIST_URLS = {
            new AntPathRequestMatcher("/h2-console/**"),
            new AntPathRequestMatcher("/api/login"),
            new AntPathRequestMatcher("/api/test"),
    };

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers(WHITE_LIST_URLS).permitAll() // Sallitaan listatut urlit

                        // Käyttäjät / vain ADMIN-tason käyttäjät
                        .requestMatchers(antMatcher("/api/kayttajat/**")).hasAuthority("ADMIN")

                        // Maksutapahtuma
                        .requestMatchers(HttpMethod.GET, "/api/maksutapahtumat/**").hasAnyAuthority("USER", "ADMIN")
                        // Vain userit voivat muokata maksutapahtumia (POST, PATCH/softdelete -pyynnöt)
                        .requestMatchers(HttpMethod.POST, "/api/maksutapahtumat/**").hasAuthority("USER")
                        .requestMatchers(HttpMethod.PATCH, "/api/maksutapahtumat/**").hasAuthority("USER")

                        // Tapahtuma
                        .requestMatchers(HttpMethod.GET, "/api/tapahtumat/**").hasAnyAuthority("USER", "ADMIN")
                        // Vain adminit voivat muokata tapahtumia (POST, PATCH, DELETE -pyynnöt)
                        .requestMatchers(HttpMethod.POST, "/api/tapahtumat/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.PATCH, "/api/tapahtumat/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/tapahtumat/**").hasAuthority("ADMIN")

                        .anyRequest().authenticated())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                .cors(cors -> cors.configurationSource(request -> {
                    // Konfiguroi CORS suoraan
                    CorsConfiguration corsConfig = new CorsConfiguration();
                    corsConfig.addAllowedOrigin("*");
                    corsConfig.addAllowedMethod("GET");
                    corsConfig.addAllowedMethod("POST");
                    corsConfig.addAllowedMethod("PATCH");
                    corsConfig.addAllowedMethod("DELETE");
                    corsConfig.addAllowedHeader("Authorization");
                    corsConfig.addAllowedHeader("Content-Type");
                    corsConfig.setAllowCredentials(false);
                    return corsConfig;
                }))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider())
                .build();
    }
}
