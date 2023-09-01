package com.villaalegre.asada.Config;

import com.villaalegre.asada.Models.Privilege;
import com.villaalegre.asada.Models.Role;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Utilities.CommonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class PrivilegeEvaluate {

    @Autowired
    private CommonMethods commonMethods;

    public static boolean hasPrivilege(Authentication authentication, String permission) {
        for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            if (grantedAuthority.getAuthority().contains(permission)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasPrivilege(String permission) {
        User user = commonMethods.getLoggedUser();

        for (Role role : user.getRoles()) {
            for (Privilege privilege : role.getPrivileges()) {
                if (permission.equals(privilege.getName())) {
                    return true;
                }
            }
        }

        return false;
    }
}
