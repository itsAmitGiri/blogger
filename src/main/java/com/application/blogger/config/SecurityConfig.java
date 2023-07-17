package com.application.blogger.config;

import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.application.blogger.model.UserEntity;
import com.application.blogger.security.CustomUserDetailService;
import com.application.blogger.security.JwtAuthenticationEntryPoint;
import com.application.blogger.security.JwtAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebSecurity
public class SecurityConfig{
	
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
		
		http
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.disable())
			.authorizeHttpRequests(auth -> auth.requestMatchers("/api/**")
					.authenticated()
					.requestMatchers("/api/v1/auth")
					.permitAll().anyRequest().authenticated())
			.exceptionHandling(ex ->
					ex.authenticationEntryPoint(jwtAuthenticationEntryPoint)	)
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
					
			
			;
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); 
		
		return http.build();
		
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		UserDetails userDetails = User.builder().username("amit").password(passwordEncode().encode("1234")).roles("ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(userDetails);
//		
//	}
	
	@Bean
	public PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception{
//		
//		return builder.getAuthenticationManager();
//	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth,AuthenticationConfiguration builder) throws Exception{
		
		auth.userDetailsService(this.customUserDetailService).passwordEncoder(passwordEncode());
		
		return builder.getAuthenticationManager();
	}

}



















