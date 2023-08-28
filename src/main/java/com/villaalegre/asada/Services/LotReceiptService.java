package com.villaalegre.asada.Services;

import com.villaalegre.asada.Models.LotReceipt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LotReceiptService {
    void save(LotReceipt lotReceipt);

    List<LotReceipt> getAllReceiptsByUserId(Long id);
}
