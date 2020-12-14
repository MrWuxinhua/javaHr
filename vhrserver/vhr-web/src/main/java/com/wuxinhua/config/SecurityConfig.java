package com.wuxinhua.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wuxinhua.model.Hr;
import com.wuxinhua.model.RespBean;
import com.wuxinhua.service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private HrService hrService;
    @Autowired
    private MyFilter myFilter;
    @Autowired
    private MyDecisionManager myDecisionManager;

    /**
     * 密码加密
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Authentication getauthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login");
    }

    /**
     * 认证管理配置
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(hrService);
    }

    /**
     * 页面跳转相关
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("http = " + http);
        http.authorizeRequests()
//                .anyRequest().authenticated()   //所有请求都需要认证
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(myDecisionManager);
                        o.setSecurityMetadataSource(myFilter);
                        return o;
                    }
                })
                .and()
                .formLogin()  //登录表单相关信息
                .usernameParameter("username")//登录时的用户名字段
                .passwordParameter("password") //登录时的密码字段
                .loginProcessingUrl("/dologin") //登录url
//                .loginPage("/login") //登录页面
                .successHandler(new AuthenticationSuccessHandler() {  //认证成功时的返回信息
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        //设置返回格式
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = resp.getWriter();
                        //获取登录成功后的用户信息
                        Hr hr = (Hr) authentication.getPrincipal();
                        hr.setPassword(null);
                        RespBean hrResp = RespBean.ok("登录成功", hr);
                        //将对象转换为字符串
                        String hrRespString = new ObjectMapper().writeValueAsString(hrResp);
                        //写入返回信息
                        writer.write(hrRespString);
                        writer.flush();
                        writer.close();
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() { //认证失败是的返回信息
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {

                        //设置返回格式
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = resp.getWriter();
                        RespBean respBean = RespBean.error("登录失败");
                        if(e instanceof LockedException){
                            respBean.setMessage("账户被锁定，请联系管理员");
                        }else if(e instanceof CredentialsExpiredException){
                            respBean.setMessage("密码已过期,请联系管理员 ");
                        }else if(e instanceof AccountExpiredException){
                            respBean.setMessage("账户已过期,请联系管理员 ");
                        }else if(e instanceof DisabledException){
                            respBean.setMessage("账户被禁用,请联系管理员 ");
                        } else if(e instanceof BadCredentialsException){
                            respBean.setMessage("用户名或密码错误，请重新输入");
                        }
                        //将对象转换为字符串
                        String hrRespString = new ObjectMapper().writeValueAsString(respBean);
                        //写入返回信息
                        writer.write(hrRespString);
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll() //从这里的接口都可以返回
                .and()
                .logout()  //注销登录
                .logoutSuccessHandler(new LogoutSuccessHandler() { //注销登录成功的返回
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                    resp.setContentType("application/json;charset=utf-8");
                        PrintWriter writer = resp.getWriter();
                        writer.write(new ObjectMapper().writeValueAsString(RespBean.ok("注销登录成功")));
                        writer.flush();
                        writer.close();
                    }
                })
                .permitAll() //从这里的接口都可以返回
                .and()
                .csrf().disable().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                resp.setStatus(401);
                PrintWriter writer = resp.getWriter();
                RespBean respBean = RespBean.error("访问失败");
                if(e instanceof InsufficientAuthenticationException){
                    respBean.setMessage("请求失败 ，请联系管理员");
                }
                //将对象转换为字符串
                String hrRespString = new ObjectMapper().writeValueAsString(respBean);
                //写入返回信息
                writer.write(hrRespString);
                writer.flush();
                writer.close();
            }
        });  //关闭csrf
    }
}
