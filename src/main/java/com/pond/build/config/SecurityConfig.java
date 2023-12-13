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

        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").permitAll()
                .antMatchers("/refreshToken/{refreshToken}").permitAll()
                //获取字段信息的
                .antMatchers("/allItems").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();

        //Todo 为什么这里不行呢？配置了以后没有验证就提示验证失败
        //2023.12.13 首先分析之前的逻辑:
        // 1.前端发json
        // 2.后端token拦截器拦截,如果有token就验证token,从token中获取用户id,然后查权限之类的交给Spring Security管理,token无效就报错,没有token就放行，请求来到controller
        // 3.controller将json转成User,然后调用service中的登录处理逻辑,验证成功就创建token,将token存在redis,并返回token,验证失败就抛出异常,进入到AuthenticationEntryPointImpl或者AccessDeniedHandlerImpl
        // 如果开启表单登录,那么流程就变成:
        // 1.这里配置的loginPage("/user/login"),Spring Security会拦截来自这个路径的表单提交请求,从中获取username和password,如果使用api工具请求,注意将Content-Type:application/x-www-form-urlencoded加上,然后使用form-data而不是json
        // 2.和之前的逻辑一样,有token就测,没有就放行,但是验证后将进入我们自定义的验证成功处理器和验证失败处理器,并不会进入我们的controller
        // 3.因为不会进行controller所以要将service中的逻辑全部搬到我们自定义的验证成功处理器和验证失败处理器中

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


