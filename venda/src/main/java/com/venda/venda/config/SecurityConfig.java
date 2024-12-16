package com.venda.venda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/cadastro").permitAll() // acesso liberado as paginas
                        .anyRequest().authenticated() // todas as outras paginas precisa de autenticação
        )
        .formLogin(form -> form
                        .loginPage("/login") // pagina de login setada
                        .defaultSuccessUrl("/home", true) // redireciona para home após login feito
                        .permitAll() // permite voltar pra login
        )
        .logout(logout -> logout
                        .logoutUrl("/logout") // url de logout
                        .logoutSuccessUrl("/login") // manda pra login depois do logout
        )
        .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
