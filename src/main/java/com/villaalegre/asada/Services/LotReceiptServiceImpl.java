package com.villaalegre.asada.Services;

import com.villaalegre.asada.DTO.LotReceiptDTO;
import com.villaalegre.asada.Models.Lot;
import com.villaalegre.asada.Models.LotReceipt;
import com.villaalegre.asada.Repository.LotReceiptRepository;
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
public class LotReceiptServiceImpl implements LotReceiptService {
    @Autowired
    private LotReceiptRepository lotReceiptRepository;

    @Autowired
    private TypeService typeService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(LotReceipt lotReceipt) { lotReceiptRepository.save(lotReceipt); }

    @Override
    public Optional<LotReceipt> findById(Long id) { return lotReceiptRepository.findById(id); }

    @Override
    public List<LotReceipt> getAllReceiptsByUserId(Long id) {
        return lotReceiptRepository.findAllByUserId(id, typeService.findByNameAndGroup("Eliminado", "receipt status").getId());
    }

    @Override
    public Optional<LotReceipt> findByReceiptId(String receiptId) {
        return lotReceiptRepository.findByReceiptId(receiptId);
    }

    public LotReceipt convertToEntity(LotReceiptDTO lotReceiptDTO) throws Exception {
        LotReceipt lotReceipt = modelMapper.map(lotReceiptDTO, LotReceipt.class);

        lotReceipt.setMonthPaid(convertStringToDate(lotReceiptDTO.getMonthToPay()));
        lotReceipt.setStatus(typeService.findByNameAndGroup(lotReceiptDTO.getStatus(), "receipt status"));
        return lotReceipt;
    }

    public LotReceiptDTO convertToDTO(LotReceipt lotReceipt) {
        LotReceiptDTO lotReceiptDTO = modelMapper.map(lotReceipt, LotReceiptDTO.class);

        lotReceiptDTO.setId(lotReceipt.getId());
        lotReceiptDTO.setStatus(lotReceipt.getStatus().getName());
        lotReceiptDTO.setMonthToPay(lotReceipt.getMonthPaid().toString());

        return lotReceiptDTO;
    }

    private Date convertStringToDate(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.parse(date);
    }
}
