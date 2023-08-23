package com.villaalegre.asada.Services;

import com.villaalegre.asada.DTO.LotDTO;
import com.villaalegre.asada.Models.Lot;
import com.villaalegre.asada.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LotService {
    List<Lot> findByUser(User user);

    Optional<Lot> findById(Long id);

    LotDTO registerNewLotOrUpdate(LotDTO lotDTO) throws Exception;

    void save(Lot lot);

    LotDTO convertToDTO(Lot lot);
}
