package com.villaalegre.asada.Services;

import com.villaalegre.asada.Models.LotReceipt;
import com.villaalegre.asada.Repository.LotReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LotReceiptServiceImpl implements LotReceiptService {
    @Autowired
    private LotReceiptRepository lotReceiptRepository;
    @Override
    public void save(LotReceipt lotReceipt) {
        lotReceiptRepository.save(lotReceipt);
    }
}
