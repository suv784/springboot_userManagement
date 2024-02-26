package com.org.UserManage.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableMethodSecurity
public class SpringConfig { 
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) { 
		
		UserDetails admin = org.springframework.security.core.userdetails.User.withUsername("ADMIN")
				.password(passwordEncoder.encode("admin@123")).build();
		UserDetails user = org.springframework.security.core.userdetails.User.withUsername("USER")
				.password(passwordEncoder.encode("suvendu")).build();
		return new InMemoryUserDetailsManager(admin, user);
	}  
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	} 
	 
	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/user/alluser").permitAll().and()
				.authorizeHttpRequests().requestMatchers("/user/save","/updateUser/{id}","/user/deleteUser/{id}","/user/fetchByName/{name}","/user/fetchBySal/{sal}","/user/gender/{gen}","/user/query/{name}","/user/bigquery/{email}","/user/delete/alluser","/user/desc/userSal","/user/asc/usersal").authenticated().and().build();
	}
	
}
