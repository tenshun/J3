package com.tenshun.configuration;

import com.tenshun.security.UserDetailsService;
import com.tenshun.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private static final String LOGOUT_URL = "/logout";

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
                .antMatchers("/").permitAll()
                .antMatchers("/join").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/resources*","/static*", "/resources/public*").permitAll()
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
                .logoutSuccessUrl("/login?logout")
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

                         Lets you specify a custom LogoutSuccessHandler.
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
                    .antMatchers("/api/authenticate").permitAll()
                    .antMatchers("/api*").authenticated()
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/**").hasRole("USER");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user2").password("password").roles("USER").and()
                .withUser("admin2").password("password").roles("USER", "ADMIN");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
    }

}