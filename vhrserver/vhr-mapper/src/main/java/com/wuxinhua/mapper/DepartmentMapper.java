package com.wuxinhua.mapper;

import com.wuxinhua.model.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> getAllDepartment(@Param("id") Integer id);

    Department getDepartmentBySelect(@Param("parentId") Integer parentId, @Param("name") String name);

    Integer updateByid(@Param("parentId") Integer parentId,@Param("b") boolean b);

    List<Department> getAllDepartmentNew();
}