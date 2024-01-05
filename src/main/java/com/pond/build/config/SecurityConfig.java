package com.pond.build.config;

import com.pond.build.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * 在 Spring Security 5.7.0-M2 中，弃用了 WebSecurityConfigurerAdapter，Spring 鼓励用户转向基于组件的安全配置。
 * 注释掉的代码,是之前的配置
 * 现在不继承了,都是以Bean的形式,还有就是不推荐.and()的写法了,现在使用Lambda写法
 *
 *
 * 引入 starter 这类jar, SpringBoot 会 自动 动过 autoconfigure 自动来加载注入 相关的security 配置信息
 * 这种方式 会自动加载对应的配置 ，可以不用 配置@EnableWebSecurity
 *
 *
 * 直接引入 对应的包
 * 其实对应的包就一共三个，可以自行配置， 比如spring mvc 项目 配置 之后 需要加入.
 *     <dependency>
 *       <groupId>org.springframework.security</groupId>
 *       <artifactId>spring-security-config</artifactId>
 *       <version>5.3.5.RELEASE</version>
 *     </dependency>
 *     <dependency>
 *       <groupId>org.springframework.security</groupId>
 *       <artifactId>spring-security-web</artifactId>
 *       <version>5.3.5.RELEASE</version>
 *     </dependency>
 *         <dependency>
 *       <groupId>org.springframework.security</groupId>
 *       <artifactId>spring-security-core</artifactId>
 *       <version>5.3.5.RELEASE</version>
 *     </dependency>
 *
 *     这种方式就要配置开启 @EnableWebSecurity 不然不生效
 */


@Configuration
//启用Spring Security的Web安全性功能  或者 继承 WebSecurityConfigurerAdapter
//@EnableWebSecurity
//开启授权注解功能
@EnableGlobalMethodSecurity(prePostEnabled =true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
public class SecurityConfig{

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


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                //关闭csrf
//                .csrf().disable()
                .csrf(AbstractHttpConfigurer::disable)
                //不通过Session获取SecurityContext
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .sessionManagement(conf->conf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .and()
//                .authorizeRequests()
                .authorizeHttpRequests(conf-> conf.requestMatchers("/user/login").permitAll()
                        .requestMatchers("/user/refresh").permitAll()
                        .requestMatchers("/allItems").permitAll()
                        .requestMatchers("/hello").permitAll()
                        .anyRequest().authenticated());
                // 对于登录接口 允许匿名访问
//                .antMatchers("/user/login").permitAll()
//                .antMatchers("/refreshToken/{refreshToken}").permitAll()
                //获取字段信息的
//                .antMatchers("/allItems").permitAll()
                //测试
//                .antMatchers("/hello").permitAll()
                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest().authenticated();

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
//        http.exceptionHandling(
//                        //认证失败处理器
//                        exceptionHand ->
//                                exceptionHand.authenticationEntryPoint(authenticationEntryPoint)
//                                        .accessDeniedHandler(accessDeniedHandler));
                //认证失败处理器
//                .authenticationEntryPoint(authenticationEntryPoint)
//                .accessDeniedHandler(accessDeniedHandler);

        //允许跨域
//        http.cors();
        http.cors(withDefaults());

        return http.build();
    }

//    @Bean  //这样子就可以从容器当中获取到AuthenticationManager
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }

    /**
     * 认证管理器，登录的时候参数会传给 authenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}


