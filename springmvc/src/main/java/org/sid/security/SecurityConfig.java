package org.sid.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("user1").password("{noop}1234").roles("USER");//Le
		// mot de passe n'est pas encode
		PasswordEncoder passwordencoder = passwordEncoder();
		System.out.println("***************************");
		System.out.println(passwordencoder.encode("1234"));
		System.out.println("***************************");
		auth.inMemoryAuthentication().withUser("user1").password(passwordencoder.encode("1234")).roles("USER");
		auth.inMemoryAuthentication().withUser("user2").password(passwordencoder.encode("1234")).roles("USER");
		auth.inMemoryAuthentication().withUser("admin").password(passwordencoder.encode("admin")).roles("USER", "ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Redefinir les regles.
		http.formLogin();// .loginPage("/login");
		// http.httpBasic();//pas de form, dialog js egalement pas de logout page.
		// http.authorizeRequests().anyRequest().authenticated();//means -> All reqs
		// need auth. Regle de gestion | passer en bas apres la config des roles
		// Si je vx que la req de l'action save soit execute que par Admin, j'applique
		// les regles de gestion suivantes:
		http.authorizeRequests().antMatchers("/save**/**", "/delete**/**","/form**/**").hasRole("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.csrf();// Par defaut il est active, .disabled() pour le desactive.
		http.exceptionHandling().accessDeniedPage("/notAuthorized");//403 forbidden error
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
