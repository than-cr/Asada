package com.villaalegre.asada.Repository;

import com.villaalegre.asada.Models.Lot;
import com.villaalegre.asada.Models.Type;
import com.villaalegre.asada.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface LotRepository extends JpaRepository<Lot, Long> {
    List<Lot> findByUser(User user);

    Optional<Lot> findById(Long id);
}
