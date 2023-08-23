package com.villaalegre.asada.Controllers;

import com.villaalegre.asada.DTO.UserDTO;
import com.villaalegre.asada.Models.Role;
import com.villaalegre.asada.Models.Type;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Services.RoleService;
import com.villaalegre.asada.Services.TypeService;
import com.villaalegre.asada.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UsersController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Secured("ROLE_STAFF")
    @GetMapping("/users")
    public String index(Model model) {
        List<User> users = userService.findAll();
        List<Type> statuses = typeService.findByGroup("user status");
        List<Role> roles = roleService.findAll();

        model.addAttribute("users", users);
        model.addAttribute("statuses", statuses);
        model.addAttribute("roles", roles);

        return "users";
    }

    @Secured("ROLE_STAFF")
    @PostMapping(value = "/user", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public UserDTO saveUser(@RequestBody UserDTO userDTO) throws Exception {
        return userService.registerNewUserOrUpdate(userDTO);
    }

    @Secured("ROLE_STAFF")
    @GetMapping(value = "/user/{userId}")
    @ResponseBody
    public UserDTO getUser(@PathVariable(value = "userId") Long userId) throws Exception {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }

        return userService.convertToDTO(user.get());
    }

}