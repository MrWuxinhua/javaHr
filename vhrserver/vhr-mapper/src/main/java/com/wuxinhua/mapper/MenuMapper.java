package com.wuxinhua.mapper;

import com.wuxinhua.model.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    List<Menu> getMenuByHrId(Integer i);

    List<Menu> getMenuAndRoles();

    List<Menu> getAllMenus();

    List<Integer> getAllMenusByRoleId(@Param("rid") Integer rid);

    void deleteMenusRoleByRoleId(@Param("rid") Integer rid);

    Integer insertMenuRole(@Param("rid") Integer rid, @Param("ids") Integer[] ids);
}