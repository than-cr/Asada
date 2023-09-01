package com.villaalegre.asada.Controllers;

import com.villaalegre.asada.Models.Privilege;
import com.villaalegre.asada.Models.Role;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class AbstractController {

    @Autowired
    private UserService userService;

    public User getLoggedUser() {
        String currentUserName;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        else {
            throw new RuntimeException("User not found");
        }

        Optional<User> user = userService.findByUsername(currentUserName);
        if (user.isEmpty())
        {
            throw new RuntimeException("User not found");
        }

        return user.get();
    }

    public boolean hasPrivilege(String permission) {
        User user = getLoggedUser();

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
