package com.application.blogger.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.application.blogger.model.UserEntity;
import com.application.blogger.security.CustomUserDetailService;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


//		http
//		.csrf()
//		.disable()
//		.authorizeHttpRequests()
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();

        
        http
                .authorizeHttpRequests((authz) -> authz
                                .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());

		return http.build();
		
    }
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
//	@Bean
//    public InMemoryUserDetailsManager userDetailsService() {
////        UserDetails user = User.withDefaultPasswordEncoder()
////            .username("user")
////            .password("password")
////            .roles("USER")
////            .build();
////        return new InMemoryUserDetailsManager(user);
//		
//		UserEntity user = User
//				.withDefaultPasswordEncoder()
//				.username(null)
//				.password(null)
//				.roles(null)
//				.build();
//    }
//	
	//@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        UserDetails user = User.withDefaultPasswordEncoder()
//            .username("user")
//            .password("password")
//            .roles("USER")
//            .build();
//        auth.inMemoryAuthentication()
//            .withUser(user);
    	
    	auth.userDetailsService(this.customUserDetailService)
    	.passwordEncoder(passwordEncoder());
    	
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
	

}



















