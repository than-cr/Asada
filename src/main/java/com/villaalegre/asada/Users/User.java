package com.villaalegre.asada.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.villaalegre.asada.Models.AbstractModel;
import com.villaalegre.asada.Models.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Collection;

@Entity
public class User extends AbstractModel {
    private String userId;
    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;
    private String lot;
    private String email;
    private String phoneNumber;
    private boolean enabled;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;
}
