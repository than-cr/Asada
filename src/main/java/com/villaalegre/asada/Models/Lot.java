package com.villaalegre.asada.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@Entity
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lot_seq")
    @SequenceGenerator(name = "lot_seq", allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "owner")
    private User user;
    @ManyToOne
    @JoinColumn(name = "status")
    private Type status;

    @Temporal(TemporalType.DATE)
    private Date lastMonthPaid;
}
