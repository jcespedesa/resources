package com.trc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class UserSecurityConfiguration extends WebSecurityConfigurerAdapter 
{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/comResources/").permitAll()
				.antMatchers("/comResources/cms/*").authenticated()
				.anyRequest().permitAll()
				.and()
			.formLogin()
				.loginPage("/comResources/cms")
				.defaultSuccessUrl("/comResources/cmsMenu",true)
				
				.failureHandler((req,res,exp)->{  // Failure handler invoked after authentication failure
                	String errMsg="";
                	if(exp.getClass().isAssignableFrom(BadCredentialsException.class))
                	{
                		errMsg="Invalid username or password.";
                	}
                	else
                	{
                		errMsg="Unknown error - "+ exp.getMessage();
                	}
                	req.getSession().setAttribute("message",errMsg);
                	res.sendRedirect("/comResources/cms"); // Redirect user to login page with error message.
                })
				
				.and()
				.exceptionHandling()
					.accessDeniedPage("/comResources/cms");
        
            
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception 
	{
	   auth
	      .inMemoryAuthentication()
	      .withUser("root").password("{noop}Lenz2142*").roles("ADMIN");
	}
	

	
}
