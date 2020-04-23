package ro.mxp.food.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceLogin userDetailsServiceLogin;

    @Autowired
    public SecurityConfiguration(UserDetailsServiceLogin userDetailsServiceLogin) {
        this.userDetailsServiceLogin = userDetailsServiceLogin;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsServiceLogin)
                .passwordEncoder(getBCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/myUser").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/restaurant/**").hasRole("RESTAURANT")
                .antMatchers(HttpMethod.DELETE, "/restaurant/**").hasAnyRole("ADMIN", "RESTAURANT")
                .antMatchers(HttpMethod.POST, "/product").hasRole("RESTAURANT")
                .antMatchers(HttpMethod.PUT, "/product/**").hasRole("RESTAURANT")
                .antMatchers(HttpMethod.DELETE, "/product/**").hasAnyRole("ADMIN", "RESTAURANT")
                .antMatchers(HttpMethod.GET, "/client").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/client").hasRole("CLIENT")
                .antMatchers(HttpMethod.DELETE, "/client/**").hasAnyRole("ADMIN", "RESTAURANT", "CLIENT")
                .antMatchers(HttpMethod.GET, "/product_in_cart").hasAnyRole("ADMIN", "CLIENT")
                .antMatchers(HttpMethod.POST, "/product_in_cart").hasRole("CLIENT")
                .antMatchers(HttpMethod.PUT, "/product_in_cart/**").hasRole("CLIENT")
                .antMatchers(HttpMethod.DELETE, "/product_in_cart/**").hasAnyRole("ADMIN", "CLIENT")
                .antMatchers(HttpMethod.GET, "/cart/pending").hasAnyRole("RESTAURANT", "CLIENT")
                .antMatchers(HttpMethod.GET, "/cart/send_command").hasRole("CLIENT")
                .antMatchers("/product_in_cart").authenticated()
                .antMatchers("/display").authenticated()
                .antMatchers("/").permitAll()
                .and()
                .httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
