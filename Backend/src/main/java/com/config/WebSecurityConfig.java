package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	private final UserDetailsService userDetailsService;
	@Autowired
	private JwtFilter jwtFilter;
	
    public WebSecurityConfig(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity
    	.csrf(csrf->csrf.disable())
    	.authorizeHttpRequests(
    			request -> request
    			.requestMatchers("/register","/login").permitAll()
    			.anyRequest().authenticated())
    	
    	.httpBasic(Customizer.withDefaults());
         return httpSecurity.addFilterBefore(jwtFilter,UsernamePasswordAuthenticationFilter.class)
        		 .build();
    }
    //@Bean
    public UserDetailsService userdetailservice() {
    	UserDetails sharmi=User.withUsername("sharmi")
    			.password("{noop}1234")
    			.roles("USER")
    			.build();
    	UserDetails harmi=User.withUsername("harmi")
    			.password("{noop}123")
    			.roles("USER")
    			.build();
    	return new InMemoryUserDetailsManager(sharmi,harmi);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
    	return new BCryptPasswordEncoder(14);
    }
    @Bean
    public  AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
    	provider.setUserDetailsService(userDetailsService);
    	//provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    	provider.setPasswordEncoder(bCryptPasswordEncoder());
    	return provider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    	return configuration.getAuthenticationManager();
    }
}
