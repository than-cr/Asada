package com.villaalegre.asada.Services;

import com.villaalegre.asada.DTO.LotReceiptDTO;
import com.villaalegre.asada.Models.Lot;
import com.villaalegre.asada.Models.LotReceipt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LotReceiptService {
    void save(LotReceipt lotReceipt);

    Optional<LotReceipt> findById(Long id);

    List<LotReceipt> getAllReceiptsByUserId(Long id);

    Optional<LotReceipt> findByReceiptId(String receiptId);

    LotReceiptDTO convertToDTO(LotReceipt lotReceipt);

    LotReceipt convertToEntity(LotReceiptDTO lotReceiptDTO) throws Exception;
}
