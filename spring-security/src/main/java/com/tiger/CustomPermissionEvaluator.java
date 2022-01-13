package com.tiger;

import com.tiger.entity.SysPersmission;
import com.tiger.task.SysPermissionService;
import com.tiger.task.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName CustomPermissionEvaluator
 * @Description TODO
 * @Author tiger
 * @Date 2019/11/18 22:28
 * @Version 1.0
 **/
@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private SysRoleService roleService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        User user = (User) authentication.getPrincipal();
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String roleName = authority.getAuthority();
            Integer roleId = roleService.selectByName(roleName).getId();
            List<SysPersmission> sysPersmissions = permissionService.listByRoleId(roleId);
            for (SysPersmission sysPersmission : sysPersmissions) {
                if (sysPersmission.getUrl().equals(targetDomainObject) && sysPersmission.getPermissions().contains(permission)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
