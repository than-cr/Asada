package com.villaalegre.asada.Utilities;

import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommonMethods {

    @Autowired
    private UserService userService;

    public CommonMethods() {}

    public User getLoggedUser() {
        String currentUserName = "";
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
}
