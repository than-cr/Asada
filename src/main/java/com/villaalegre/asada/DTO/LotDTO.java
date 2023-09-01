package com.villaalegre.asada.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LotDTO {
    private Long id;
    private String name;
    private String owner;
    private String status;
    private String lastMonthPaid;
    private double payment;
    private String receiptId;
    private long monthsInDebt;
}
