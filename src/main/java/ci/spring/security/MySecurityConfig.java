/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ci.spring.security;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author sglo
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public DataSource dataSource;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("user")
//                .authorities("USER")
//                .and()
//                .withUser("admin")
//                .password("admin")
//                .authorities("ADMIN");
//
//    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//       auth.inMemoryAuthentication()
//                .withUser("user")
//                .password("$2a$10$IzAxvs/Uts3Y7/Ltc6qDu..bVrR3BaVtCyLZi4i1mPDpsCNMPukd6")
//                .roles("USER")
//                .and()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin")
//                .password(this.passwordEncoder.encode("admin"))
//                .roles("ADMIN")
//                .and()
//                .passwordEncoder(new BCryptPasswordEncoder());

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select username,role from user_roles where username=?")
//                .usersByUsernameQuery(
//                        "select username as principal, password as credentials, true from users where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username as principal, roles as role from users_roles where username=?")
//                .rolePrefix("ROLE_")
                        .passwordEncoder(new BCryptPasswordEncoder());

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//   auth.inMemoryAuthentication()
//                .withUser("user")
//                .password(this.passwordEncoder.encode("user"))
//                .roles("USER")
//                .and()
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("admin")
//                .password(this.passwordEncoder.encode("admin"))
//                .roles("ADMIN")
//                .and()
//                .passwordEncoder(new BCryptPasswordEncoder());
//    }

//   @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username as principal, password as credentials, true from users where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username as principal, roles as role from users_roles where username=?")
//                .rolePrefix("ROLE_");
//                        .passwordEncoder(new Md5PasswordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/")
//                .access("hasAnyRole('USER','ADMIN') ")
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/access_denied");
//                        
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/benef/editer/").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //                .loginPage("/login")
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable();
        http.exceptionHandling().accessDeniedPage("/403");

//                .authorizeRequests()
//                .antMatchers("/")
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
    }

}
