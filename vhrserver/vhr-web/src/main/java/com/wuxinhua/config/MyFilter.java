package com.wuxinhua.config;

import com.wuxinhua.model.Menu;
import com.wuxinhua.model.Role;
import com.wuxinhua.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 根据发送的请求地址，分析所需要的角色
 */

@Component
public class MyFilter  implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;
//
//    @Autowired
//    private AntPathMatcher antPathMatcher;


    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {



        AntPathMatcher     antPathMatcher = new AntPathMatcher();


        //获取到请求路径
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //获取所有菜单中的url
        List<Menu> menus = menuService.getMenuAndRoles();
        for (Menu menu : menus) {
            //如果请求参路径与菜单中的路径匹配
            if (antPathMatcher.match(menu.getUrl(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                //创建返回集合
                String[] roleStrs = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    roleStrs[i] = roles.get(i).getName();
                }
                return SecurityConfig.createList(roleStrs);
            }
        }
        return SecurityConfig.createList("LOGIN_ROLES");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
