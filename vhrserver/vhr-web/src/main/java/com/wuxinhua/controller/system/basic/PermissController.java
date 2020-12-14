package com.wuxinhua.controller.system.basic;

import com.wuxinhua.model.Menu;
import com.wuxinhua.model.RespBean;
import com.wuxinhua.model.Role;
import com.wuxinhua.service.MenuService;
import com.wuxinhua.service.system.basic.MenuRoleService;
import com.wuxinhua.service.system.basic.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限组
 */
@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRoleService menuRoleService;


    /**
     * 获取所有角色信息
     * @return
     */
    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    /**
     * 获取所有菜单信息
     * @return
     */
    @GetMapping("/menus/")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/role/{rid}")
    public List<Integer> getAllMenusByRoleId(@PathVariable("rid") Integer rid){
        return menuService.getAllMenusByRoleId(rid);

    }


    @PutMapping("/")
    public RespBean updateMenusRole(Integer rid , Integer[] ids){
      Boolean b =  menuService.updateMenusRole(rid,ids);
      if(b) {
          return RespBean.ok("更新成功");
      }else{
          return RespBean.error("更新失败");
      }

    }

    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role){
        Integer i = menuRoleService.addRole(role);

        if(i == 1){
            return RespBean.ok("添加成功");
        }else{
            return RespBean.error("添加失败");
        }
    }

    @DeleteMapping("/role/{id}")
    public RespBean deleteRoleById(@PathVariable(value = "id",required = true) Integer id){

        if(1==menuRoleService.deleteRoleById(id)){
            return RespBean.ok("删除成功");
        }else{
            return RespBean.error("删除失败");
        }

    }
}
