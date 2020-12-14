package com.wuxinhua.service;

import com.wuxinhua.mapper.HrMapper;
import com.wuxinhua.mapper.HrRoleMapper;
import com.wuxinhua.mapper.RoleMapper;
import com.wuxinhua.model.Hr;
import com.wuxinhua.model.HrRole;
import com.wuxinhua.model.Role;
import com.wuxinhua.service.utils.HrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HrService implements UserDetailsService {

    @Autowired
    private HrMapper hrMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private HrRoleMapper hrRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return hr;
    }

    public List<Hr> getHrAll(String keyWords) {

        return hrMapper.getHrAll(keyWords , HrUtil.getHr().getId());

    }

    public Integer updateRoleEnabledById(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    public List<Role> getAllRole() {
        return roleMapper.getAllRoles();
    }

    @Transactional
    public Integer updateHrRoles(Integer hid, Integer[] rids) {

        //删除原数据
        hrRoleMapper.deleteByHid(hid);

        //添加新数据
        int i = 0;
        for (Integer rid : rids) {
            if (1 == hrRoleMapper.insertSelective(new HrRole(null, hid, rid))) {
                i++;
            }
        }
        return i;
    }

    public Integer deleteHrById(Integer id) {
        return hrMapper.deleteHrById(id);
    }
}
