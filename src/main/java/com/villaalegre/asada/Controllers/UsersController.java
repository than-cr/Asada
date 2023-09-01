package com.villaalegre.asada.Controllers;

import com.villaalegre.asada.Config.PrivilegeEvaluate;
import com.villaalegre.asada.DTO.UserDTO;
import com.villaalegre.asada.Models.Role;
import com.villaalegre.asada.Models.Type;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Services.RoleService;
import com.villaalegre.asada.Services.TypeService;
import com.villaalegre.asada.Services.UserService;
import com.villaalegre.asada.Utilities.CommonMethods;
import com.villaalegre.asada.Utilities.CommonValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UsersController {

    private static final String OBJECT_NAME = "users";

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CommonMethods commonMethods;

    @Autowired
    private PrivilegeEvaluate privilegeEvaluate;

    @PreAuthorize("hasPermission('Users', 'View users')")
    @GetMapping("/" + OBJECT_NAME)
    public String index(Model model) {
        List<User> users = userService.findAll();
        List<Type> statuses = typeService.findByGroup("user status");
        List<Role> roles = roleService.findAll();

        if (!commonMethods.getLoggedUser().getUsername().equals(CommonValues.SUPER_ADMIN)) {
            Optional<User> userAdmin = userService.findByUsername("116480417");
            userAdmin.ifPresent(users::remove);
            Optional<Role> roleAdmin = roleService.findByName("Admin");
            roleAdmin.ifPresent(roles::remove);
        }

        model.addAttribute("addUserPrivilege", privilegeEvaluate.hasPrivilege("Add user"));
        model.addAttribute("editUserPrivilege", privilegeEvaluate.hasPrivilege("Edit user"));
        model.addAttribute("viewLots", privilegeEvaluate.hasPrivilege("View lots"));
        model.addAttribute(OBJECT_NAME, users);
        model.addAttribute("statuses", statuses);
        model.addAttribute("roles", roles);

        return OBJECT_NAME;
    }

    @PreAuthorize("hasPermission('Users', 'Add user, Edit user')")
    @PostMapping(value = "/" + OBJECT_NAME, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public UserDTO saveUser(@RequestBody UserDTO userDTO) throws Exception {
        return userService.registerNewUserOrUpdate(userDTO);
    }

    @PreAuthorize("hasPermission('Users', 'Edit user')")
    @GetMapping(value = "/" + OBJECT_NAME + "/{userId}")
    @ResponseBody
    public UserDTO getUser(@PathVariable(value = "userId") Long userId) throws Exception {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }

        return userService.convertToDTO(user.get());
    }

}