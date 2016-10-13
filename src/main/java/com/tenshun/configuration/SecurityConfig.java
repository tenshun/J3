package com.tenshun.configuration;

import com.tenshun.security.UserDetailsService;
import com.tenshun.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private static final String LOGOUT_URL = "/quit";

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/test", "/join").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .anyRequest().authenticated()
                    .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/welcome")
                    //TODO failure url/ success url
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                    .and()


                .logout()
                //.logoutUrl(LOGOUT_URL)
                //.logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies(Constants.JSESSION_ID)
                        /**
                         * 1
                         Provides logout support. This is automatically applied when using
                         WebSecurityConfigurerAdapter.

                         The URL that triggers log out to occur (default is /logout).
                         If CSRF protection is enabled (default), then the request must also be a POST.
                         For for information, please consult the JavaDoc.

                         The URL to redirect to after logout has occurred.
                         The default is /login?logout.

                         Let’s you specify a custom LogoutSuccessHandler.
                         If this is specified, logoutSuccessUrl() is ignored.

                         Specify whether to invalidate the HttpSession at the time of logout.
                         This is true by default. Configures the SecurityContextLogoutHandler under the covers.

                         Adds a LogoutHandler.
                         SecurityContextLogoutHandler is added as the last LogoutHandler by default.

                         Allows specifying the names of cookies to be removed on logout success.
                         This is a shortcut for adding a CookieClearingLogoutHandler explicitly.
                         */
                    .and()
                .authorizeRequests()
                .antMatchers("/resources/**", "/join", "/about").permitAll()
                    .antMatchers("/api/register").permitAll()
                    .antMatchers("/api/authenticate").permitAll()
                    .antMatchers("/api/account/reset_password/init").permitAll()
                    .antMatchers("/api/account/reset_password/finish").permitAll()
                    .antMatchers("/api/profile-info").permitAll()
                    .antMatchers("/api*").authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/**").hasRole("USER");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }
}