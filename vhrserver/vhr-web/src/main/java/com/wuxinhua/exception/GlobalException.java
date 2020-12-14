package com.wuxinhua.exception;


import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.wuxinhua.model.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 定义全局异常
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException sqlException) {
        if(sqlException instanceof MySQLIntegrityConstraintViolationException){
           return RespBean.error("有关联数据存在，删除失败!");
        }else{
//          return RespBean.error("数据库异常，删除失败!");
            return RespBean.error(sqlException.getNextException().toString());
        }
    }


}
