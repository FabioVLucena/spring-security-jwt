package com.aeon.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aeon.security.security.JwtAuthFilter;
import com.aeon.security.security.JwtService;
import com.aeon.security.service.UsuarioServiceImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private PasswordEncoder encoder;

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usuarioService)
            .passwordEncoder(encoder);
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuarios/**")
                    .permitAll()
                .anyRequest().authenticated()   
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
        ;
    }

}
