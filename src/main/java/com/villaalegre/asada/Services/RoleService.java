package com.villaalegre.asada.Services;

import com.villaalegre.asada.Models.Role;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoleService {
    Optional<Role> findById(Long id);
    Optional<Role> findByName(String name);

    List<Role> findAll();
}
