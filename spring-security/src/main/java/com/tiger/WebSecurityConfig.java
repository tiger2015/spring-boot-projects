package com.tiger;

import com.tiger.handler.CustomAuthenticationFailureHandler;
import com.tiger.handler.CustomAuthenticationSuccessHandler;
import com.tiger.handler.CustomLogoutSuccessHandler;
import com.tiger.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * @ClassName WebSecurityConfig
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/13 22:27
 * @Version 1.0
 **/
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;


    @Bean
    public RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ADMIN > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Bean
    public SessionRegistry sessionRegistry(){
        return new SessionRegistryImpl();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }


    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }

    @Override
    public void init(WebSecurity web) throws Exception {
        super.init(web);
        web.expressionHandler(webSecurityExpressionHandler());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 设置用户密码加密
         //auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/getVerifyCode") // 验证码
                .permitAll() // 允许全部请求
                .anyRequest()
                .authenticated()
               // .and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
               // .failureUrl("/login/error")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(authenticationSuccessHandler)// 登录成功的处理
                .failureHandler(authenticationFailureHandler)// 登录失败的处理
                .authenticationDetailsSource(authenticationDetailsSource)
                .and()
               // .addFilterBefore(new VerifyCodeFilter(), UsernamePasswordAuthenticationFilter.class)
                .logout()
                .deleteCookies("JSESSIONID") // 退出时清除浏览器的cookie
                .logoutSuccessHandler(logoutSuccessHandler) // 退出成功时的处理器
                .permitAll()
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)
                .userDetailsService(userDetailsService)// 自动登录
                .and()
                .sessionManagement() // 开启session管理
                .invalidSessionUrl("/invalid") //session过期跳转URL
                .maximumSessions(1) // 最大sessions数目
                .maxSessionsPreventsLogin(false)//当达到最大session数时是否保留已经登录的用户
                .expiredSessionStrategy(new CustomExpiredSessionStrategy())// 当达到最大值，旧用户被踢出的操作
                .sessionRegistry(sessionRegistry());  // session注册策略
        // 关闭CSRF跨域访问
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/*", "/js/**");
    }
}
