package com.tiger.service;

import com.tiger.entity.SysRole;
import com.tiger.entity.SysUser;
import com.tiger.entity.SysUserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName CustomUserDetailsService
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/13 22:22
 * @Version 1.0
 **/
@Service("userDetailsService")
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("user authentic, user{}",username);
        SysUser sysUser = sysUserService.selectByName(username);
        if(sysUser == null){
            throw new UsernameNotFoundException("username not exists");
        }
        List<SysUserRole> sysUserRoles = sysUserRoleService.listByUserId(sysUser.getId());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(SysUserRole sysUserRole: sysUserRoles){
            SysRole sysRole = sysRoleService.selectById(sysUserRole.getRoleId());
            authorities.add(new SimpleGrantedAuthority(sysRole.getName()));
        }
        return new User(sysUser.getName(),sysUser.getPassword(), authorities);
    }
}
