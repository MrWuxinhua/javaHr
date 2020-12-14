package com.wuxinhua.service.utils;


import com.wuxinhua.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * hr工具类
 */
public class HrUtil {

    public static Hr getHr(){
        return (Hr)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
