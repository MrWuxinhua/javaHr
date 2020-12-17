package com.wuxinhua.mapper;

import com.wuxinhua.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> getEmpLoyees(@Param("id") Integer id);

    List<Employee> getAllEmpBySearch(@Param("page") Integer page, @Param("size") Integer size, @Param("employee") Employee employee ,@Param("beginAndEndDate") Date[] beginAndEndDate );

    Long getEmployeeTotal(@Param("employee") Employee employee ,@Param("beginAndEndDate") Date[] beginAndEndDate);

    Integer getMaxWorkId();

    Integer addEmployeeList(@Param("employees") List<Employee> employees);

    Employee getEmployeeDetaliById(@Param("id") Integer id);
}