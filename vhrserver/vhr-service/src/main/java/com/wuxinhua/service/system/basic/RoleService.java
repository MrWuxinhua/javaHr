package com.wuxinhua.service.system.basic;

import com.wuxinhua.mapper.RoleMapper;
import com.wuxinhua.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限组
 */
@Service
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<Role> getAllRoles() {

        return roleMapper.getAllRoles();
    }
}
