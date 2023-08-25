package com.villaalegre.asada.Repository;

import com.villaalegre.asada.Models.LotReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface LotReceiptRepository extends JpaRepository<LotReceipt, Long> {

}
