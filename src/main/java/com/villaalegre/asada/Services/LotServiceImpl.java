package com.villaalegre.asada.Services;

import com.villaalegre.asada.DTO.LotDTO;
import com.villaalegre.asada.Models.Lot;
import com.villaalegre.asada.Models.Type;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Repository.LotRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LotServiceImpl implements LotService {
    @Autowired
    private LotRepository lotRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TypeService typeService;

    @Override
    public List<Lot> findByUser(User user) { return lotRepository.findByUser(user); }

    @Override
    public Optional<Lot> findById(Long id) { return lotRepository.findById(id); }

    @Override
    public LotDTO registerNewLotOrUpdate(LotDTO lotDTO) throws Exception {
        Lot lot = convertToEntity(lotDTO);

        if (null != lotDTO.getId()) {
            Optional<Lot> lotFound = lotRepository.findById(lotDTO.getId());

            if (lotFound.isPresent()) {
                Lot existingLot = lotFound.get();
                lot.setId(existingLot.getId());
            }
        }

        this.save(lot);
        return lotDTO;
    }

    @Override
    public void save(Lot lot) {
        lotRepository.save(lot);
    }

    public LotDTO convertToDTO(Lot lot) {
        LotDTO lotDTO = modelMapper.map(lot, LotDTO.class);
        lotDTO.setStatus(lot.getStatus().getName());
        lotDTO.setOwner(lot.getUser().getUsername());
        lotDTO.setLastMonthPaid(lot.getLastMonthPaid().toString());

        return lotDTO;
    }

    public Lot convertToEntity(LotDTO lotDTO) throws Exception {
        Lot lot = modelMapper.map(lotDTO, Lot.class);

        Optional<User> user = userService.findByUsername(lotDTO.getOwner());
        if (user.isEmpty()) {
            throw new Exception("User not found");
        }
        lot.setUser(user.get());

        Type status = typeService.findByNameAndGroup(lotDTO.getStatus(), "lot status");
        lot.setStatus(status);

        lot.setLastMonthPaid(convertStringToDate(lotDTO.getLastMonthPaid()));

        return lot;
    }

    private Date convertStringToDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date);
    }
}
