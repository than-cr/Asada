package com.villaalegre.asada.Services;

import com.villaalegre.asada.Models.LotReceipt;
import org.springframework.stereotype.Service;

@Service
public interface LotReceiptService {
    void save(LotReceipt lotReceipt);
}
