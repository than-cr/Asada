package com.villaalegre.asada.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.Collection;

@Entity
public class Privilege extends AbstractModel {
    private String name;
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
