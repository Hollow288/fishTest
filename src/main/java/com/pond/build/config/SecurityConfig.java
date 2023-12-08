package com.pond.build.config;

import com.pond.build.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
//启用Spring Security的Web安全性功能  或者 继承 WebSecurityConfigurerAdapter
//@EnableWebSecurity
//开启授权注解功能
@EnableGlobalMethodSecurity(prePostEnabled =true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        //todo 为什么这里不行呢？配置了以后没有验证就提示验证失败
//        http.formLogin()
//                //配置认证成功处理器
//                .successHandler(authenticationSuccessHandler)
//                .loginPage("/user/login") // 指定自定义的登录页面
//                //配置认证失败处理器
//                .failureHandler(authenticationFailureHandler);
//
//        //配置注销成功处理器
//        http.logout().logoutSuccessHandler(logoutSuccessHandler).logoutUrl("/user/logout");
//
//        //因为重写了 所以需要手动添加认证规则
//        http.authorizeRequests().anyRequest().authenticated();

        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                .antMatchers("/selectTeacherAndStudentById").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        //todo 为什么这里不行呢？配置了以后没有验证就提示验证失败
//                .and()
//                .formLogin()
//                //配置认证成功处理器
//                .successHandler(authenticationSuccessHandler)
//                .loginPage("/user/login") // 指定自定义的登录页面
//                //配置认证失败处理器
//                .failureHandler(authenticationFailureHandler)
//                .and()
//                .logout().logoutSuccessHandler(logoutSuccessHandler)
//                .logoutUrl("/user/logout");

        //把token校验过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //配置异常处理器
        http.exceptionHandling()
                //认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);

        //允许跨域
        http.cors();


    }




    @Bean  //这样子就可以从容器当中获取到AuthenticationManager
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}


