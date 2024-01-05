package com.learn.springbootsecuritylear.congif;

import com.learn.springbootsecuritylear.services.CustommUserdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.CsrfDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.beans.Customizer;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfiguration{


    @Autowired
    private CustommUserdetailService custommUserdetailService;
    // username = user (Default)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests((authz)-> authz
                        .requestMatchers("/signin").permitAll()
                        .requestMatchers("/public/**").hasRole("NORMAL")
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/signin")
                                .loginProcessingUrl("/dologin")
                                .defaultSuccessUrl("/users/")
                                .permitAll(formLogin.isCustomLoginPage())
                );
//                .httpBasic(Customizer.withDefaults());
        return http.build();
    }



    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(custommUserdetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    //Role - High Level OverView -> NORMAL : READ
    //Authority - permission -> READ
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(){
//        UserDetails userDetails = User.withDefaultPasswordEncoder()
//                .username("john")
//                .password("deepak")
//                .roles("NORMAL")
//                .build();
//        UserDetails userDetails2 = User.withDefaultPasswordEncoder()
//                .username("Anurag")
//                .password("adminA")
//                .roles("ADMIN")
//                .build();
//        List<UserDetails> userDetailsList = new ArrayList<>();
//        userDetailsList.add(userDetails);
//        userDetailsList.add(userDetails2);
//        return new InMemoryUserDetailsManager(userDetailsList);
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder(10);
//    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
