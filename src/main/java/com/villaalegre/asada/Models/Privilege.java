package com.villaalegre.asada.Models;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Entity
@Getter
@Setter
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "privilege_seq")
    @SequenceGenerator(name = "privilege_seq", allocationSize = 1)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String group;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
