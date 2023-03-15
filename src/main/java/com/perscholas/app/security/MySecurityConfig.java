
package com.perscholas.app.security;


        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
        import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
        import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.web.SecurityFilterChain;
        import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfig {

    @Autowired
    MyUserDetailsService myUserDetailsService;


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(myUserDetailsService);
        return provider;
    }

/*
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers("/").hasAnyRole("USER","ADMIN")
                .requestMatchers("/admin/**","/view_product","/add_product","/save_product","/update_product/**","/delete_product/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/index").loginProcessingUrl("/p").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/403")

        ;
    }
*/


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/css/**", "/js/**", "/images/**","/index","/index/**","/register","/saveUser","/images","/logout").permitAll()
                        .requestMatchers( "/image/**","/view_product","/add_product","/view_category","/add_category","/view_all_users","/view_all_orders").hasRole("ADMIN")
                        .requestMatchers("/user/**","/user_account","/view_order","/checkout","/update_product/**").hasAnyRole("USER","ADMIN")
                        .anyRequest().authenticated()
                )
              .formLogin((form) -> form
                        .loginPage("/index")
                        .usernameParameter("loginEmail")
                        .passwordParameter("loginPassword")
                        .loginProcessingUrl("/index/processing")
                        .defaultSuccessUrl("/getUser")
                        .failureUrl("/error?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .permitAll()).exceptionHandling().accessDeniedPage("/403");



        return http.build();
    }


}































/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfig {
    @Autowired
    AppUserDetailService appUserDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(appUserDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/","/index/**","/css/**","/js/**","/images/**","/static/**","/**").permitAll()
                        .requestMatchers("/admin/**","view_product","/add_product","/add_category").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/index")
                        .usernameParameter("loginEmail")
                        .passwordParameter("loginPassword")
                        .loginProcessingUrl("/index/getUser")
                        .defaultSuccessUrl("/home")
                        .failureUrl("/error?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .permitAll()).exceptionHandling().accessDeniedPage("/403");



        return http.build();
    }

}




*/






/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MySecurityConfig {
    @Autowired
    AppUserDetailService appUserDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(appUserDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/","/index","/css/**","/js/**","/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/index")
                        .usernameParameter("loginEmail")
                        .passwordParameter("loginPassword")
                        .loginProcessingUrl("/index/processing")
                        .defaultSuccessUrl("/index/home")
                        .failureUrl("/error?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .permitAll()).exceptionHandling().accessDeniedPage("/403");



        return http.build();
    }



}*/
