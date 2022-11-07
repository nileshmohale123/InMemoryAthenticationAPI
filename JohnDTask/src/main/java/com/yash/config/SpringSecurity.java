package com.yash.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("someshwar")
		.password("somesh@1234")
		.roles("Admin")
		.and()
		.withUser("anurag")
		.password("anurag@1234")
		.roles("User");
	}
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception { http
	 * .csrf().disable() .cors().disable() .authorizeHttpRequests()
	 * .antMatchers(HttpMethod.POST).hasAnyRole("Admin")
	 * .antMatchers(HttpMethod.GET).hasAnyRole("Admin","User"); }
	 */
	
	// Configuring the api
    // according to the roles.
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
        csrf().disable()
        .cors().disable()
        .
                httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/postEmployee").hasRole("Admin")
                .antMatchers("/getEmployee").hasAnyRole("Admin","User")
                .and()
                .formLogin();
    }

    // Function to encode the password
    // assign to the particular roles.
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
