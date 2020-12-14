package com.wuxinhua.service.system.basic;


import com.wuxinhua.mapper.RoleMapper;
import com.wuxinhua.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuRoleService {

    @Autowired
    private RoleMapper roleMapper;


    public Integer addRole(Role role) {
        if(!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        return roleMapper.insertSelective(role);
    }

    public Integer deleteRoleById(Integer id) {
        return roleMapper.deleteByPrimaryKey(id);
    }
}
