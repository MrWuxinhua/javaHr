package com.wuxinhua.service;

import com.wuxinhua.mapper.MenuMapper;
import com.wuxinhua.model.Hr;
import com.wuxinhua.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 菜单系统
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public List<Menu> getMenuByHrId(){
        //获取用户信息
        Hr hr = (Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  menuMapper.getMenuByHrId(hr.getId());
    };


    /**
     * 获取所有菜单及相关的角色信息
     * @return
     */
    public List<Menu> getMenuAndRoles(){
        return menuMapper.getMenuAndRoles();
    }


    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<Integer> getAllMenusByRoleId(Integer rid) {
        return menuMapper.getAllMenusByRoleId(rid);
    }

    @Transactional
    public Boolean updateMenusRole(Integer rid, Integer[] ids) {
        //删除原数据
        menuMapper.deleteMenusRoleByRoleId(rid);
        //添加数据
        if(ids == null || ids.length == 0){
            return true;
        }
        Integer resoult  = menuMapper.insertMenuRole(rid , ids);
            return resoult == ids.length;


    }
}
