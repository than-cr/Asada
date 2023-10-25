package com.villaalegre.asada.Repository;

import com.villaalegre.asada.Models.LotReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface LotReceiptRepository extends JpaRepository<LotReceipt, Long> {

    @Query("SELECT e FROM LotReceipt e, Lot l WHERE e.lot.id = l.id AND l.user.id = :UserId AND e.status.id != :StatusId order by e.createdDate DESC")
    List<LotReceipt> findAllByUserId(Long UserId, Long StatusId);

    @Query("SELECT e FROM LotReceipt  e WHERE e.receiptId = :receiptId")
    Optional<LotReceipt> findByReceiptId(String receiptId);
}
