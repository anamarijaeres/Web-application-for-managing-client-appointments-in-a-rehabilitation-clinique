package opp.flow;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/").permitAll();
        http.headers().frameOptions().sameOrigin(); // fixes h2-console problem
        http.csrf().disable();
    }
    
//    @Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable();
//		http 
//			.authorizeRequests()
//			.antMatchers("/", "/welcome", "/register", "/addUser**")
//			.permitAll().anyRequest().authenticated()
//			.and()
//			.formLogin().loginPage("/login").permitAll()
//			.and()
//			.logout()
//			.permitAll(); 
//	}
    
}
