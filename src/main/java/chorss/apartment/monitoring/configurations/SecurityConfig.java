package chorss.apartment.monitoring.configurations;

import chorss.apartment.monitoring.service.accounts.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailsService customUserDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //ALL
                .antMatchers("/", "/home", "/register").permitAll()

                //LIB
                .antMatchers("/js/**", "/css/**", "/fonts/**").permitAll()
                .antMatchers("/api/**", "/h2/**").permitAll()

                //SWAGGER
                .antMatchers("/v2/api-docs", "/swagger*/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .csrf().ignoringAntMatchers("/api/**")
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}