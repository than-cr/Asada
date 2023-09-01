package com.villaalegre.asada.Controllers;

import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String Index()
    {
        return "redirect:/login";
    }

    @PreAuthorize("hasPermission('Home', 'View home')")
    @GetMapping("/home")
    public String Home(Model model)
    {
        return "home";
    }
}
