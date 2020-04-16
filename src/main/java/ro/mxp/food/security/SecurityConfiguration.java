package ro.mxp.food.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
                // 1
                .antMatchers("/myUser").hasRole("ADMIN")
                // 2
                .antMatchers("/restaurant").hasAnyRole("ADMIN", "RESTAURANT")
                // 3
                .antMatchers("/product").hasAnyRole("ADMIN", "RESTAURANT")
                // 4
                .antMatchers("/client").hasAnyRole("ADMIN", "CLIENT")
                // 5
                .antMatchers("/").authenticated()
                .and()
                .httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

}
