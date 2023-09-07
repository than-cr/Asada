package com.villaalegre.asada.Controllers;

import com.villaalegre.asada.Config.PrivilegeEvaluate;
import com.villaalegre.asada.DTO.UserDTO;
import com.villaalegre.asada.Models.Role;
import com.villaalegre.asada.Models.Type;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Services.RoleService;
import com.villaalegre.asada.Services.TypeService;
import com.villaalegre.asada.Services.UserService;
import com.villaalegre.asada.Utilities.CommonValues;
import com.villaalegre.asada.Utilities.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UsersController extends AbstractController {

    private static final String OBJECT_NAME = "users";

    @Autowired
    private TypeService typeService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PrivilegeEvaluate privilegeEvaluate;

    @PreAuthorize("hasPermission('Users', 'View users')")
    @GetMapping("/" + OBJECT_NAME)
    public String index(Model model, Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), 5);

        PageWrapper<User> page = new PageWrapper<>(userService.findAll(pageable), "/users");
        List<Type> statuses = typeService.findByGroup("user status");
        List<Role> roles = roleService.findAll();

        if (!getLoggedUser().getUsername().equals(CommonValues.SUPER_ADMIN)) {
            Optional<Role> roleAdmin = roleService.findByName("Admin");
            roleAdmin.ifPresent(roles::remove);
        }

        model.addAttribute("addUserPrivilege", hasPrivilege("Add user"));
        model.addAttribute("editUserPrivilege", hasPrivilege("Edit user"));
        model.addAttribute("viewLots", hasPrivilege("View lots"));
        model.addAttribute(OBJECT_NAME, page.getPage());
        model.addAttribute("page", page);
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