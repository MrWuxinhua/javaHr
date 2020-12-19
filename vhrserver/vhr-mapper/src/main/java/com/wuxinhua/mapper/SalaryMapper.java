package com.wuxinhua.mapper;

import com.wuxinhua.model.Salary;
import com.wuxinhua.model.SalarySobCfg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Salary record);

    int insertSelective(Salary record);

    Salary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Salary record);

    int updateByPrimaryKey(Salary record);

    List<Salary> getAllSalary();

    List<SalarySobCfg> getAllSalarySobCfgs(@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

    Long getSalarySobCfgListTotal();
}