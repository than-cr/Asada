package com.villaalegre.asada.Controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController extends AbstractController {
    private static final String OBJECT_NAME = "roles";

    @PreAuthorize("hasPermission('Roles','View roles')")
    @GetMapping("/" + OBJECT_NAME)
    public String index() {
        return OBJECT_NAME;
    }
}
