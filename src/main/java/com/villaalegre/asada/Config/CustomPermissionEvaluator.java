package com.villaalegre.asada.Config;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;

public class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if ((null == authentication) || (null == targetDomainObject) || !(permission instanceof String)) {
            return false;
        }

        if (((String) permission).contains(",")) {
            String[] permissions = permission.toString().split(",");

            for (String permissionSplitted: permissions) {
                if (PrivilegeEvaluate.hasPrivilege(authentication, permissionSplitted)) {
                    return true;
                }
            }

            return false;
        }

        return PrivilegeEvaluate.hasPrivilege(authentication, permission.toString());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if ((null == authentication) || (null == targetType) || !(permission instanceof String)) {
            return false;
        }

        return PrivilegeEvaluate.hasPrivilege(authentication, permission.toString());
    }
}
