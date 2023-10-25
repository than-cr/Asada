package com.villaalegre.asada.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LotReceiptDTO {
    private Long id;
    private String receiptId;
    private String status;
    private String monthToPay;
    private double cost;
}
