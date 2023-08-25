package com.villaalegre.asada.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class LotReceipt {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lot_id")
    private Lot lot;
    private String receiptId;
    private Date MonthPaid;
    private double cost;
}
