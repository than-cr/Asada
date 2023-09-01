package com.villaalegre.asada.Config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class PrivilegeEvaluate {

    public static boolean hasPrivilege(Authentication authentication, String permission) {
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            if (grantedAuthority.getAuthority().contains(permission)) {
                return true;
            }
        }
        return false;
    }
}
