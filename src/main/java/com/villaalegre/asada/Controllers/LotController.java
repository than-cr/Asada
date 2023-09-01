package com.villaalegre.asada.Controllers;

import com.villaalegre.asada.DTO.LotDTO;
import com.villaalegre.asada.Models.Lot;
import com.villaalegre.asada.Models.LotReceipt;
import com.villaalegre.asada.Models.Type;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Services.LotReceiptService;
import com.villaalegre.asada.Services.LotService;
import com.villaalegre.asada.Services.TypeService;
import com.villaalegre.asada.Services.UserService;
import com.villaalegre.asada.Utilities.CommonValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class LotController {

    private static final String OBJECT_NAME = "lots";

    @Autowired
    private LotService lotService;

    @Autowired
    private LotReceiptService lotReceiptService;

    @Autowired
    private UserService userService;

    @Autowired
    private TypeService typeService;

    @PreAuthorize("hasPermission('Lots', 'View lots')")
    @GetMapping(value = "/" + OBJECT_NAME + "/{userId}")
    public String index(Model model, @PathVariable(value = "userId") Long userId) throws Exception {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()) {
            throw new Exception();
        }

        String userFullName = user.get().getFirstName() + " " + user.get().getLastName() + " " + user.get().getMotherLastName();

        List<Lot> lots = lotService.findByUser(user.get());
        List<Type> statuses = typeService.findByGroup("lot status");

        model.addAttribute("ownerId", user.get().getUsername());
        model.addAttribute("owner", userFullName);
        model.addAttribute("statuses", statuses);
        model.addAttribute("lots", lots);

        return "lots";
    }

    @PreAuthorize("hasPermission('Lots', 'Edit lot')")
    @GetMapping(value = "/" + OBJECT_NAME + "/view/{lotId}")
    @ResponseBody
    public LotDTO getLot(@PathVariable(value = "lotId") Long lotId) throws Exception {
        Optional<Lot> lot = lotService.findById(lotId);

        if (lot.isEmpty()) {
            throw new Exception("Lot not found");
        }

        return lotService.convertToDTO(lot.get());
    }

    @PreAuthorize("hasPermission('Lots', 'Add lot, Edit lot')")
    @PostMapping(value = "/" + OBJECT_NAME, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public LotDTO saveLot(@RequestBody LotDTO lotDTO) throws Exception {
        return lotService.registerNewLotOrUpdate(lotDTO);
    }

    @PreAuthorize("hasPermission('Lots', 'Edit lot')")
    @GetMapping(value = "/" + OBJECT_NAME + "/payment/{lotId}")
    @ResponseBody
    public LotDTO getLotReceipt(@PathVariable(value = "lotId") Long lotId) throws Exception {
        Optional<Lot> lot = lotService.findById(lotId);
        if (lot.isEmpty()) {
            throw new Exception("Lot not found");
        }

        Lot lotFound = lot.get();
        addMonthToLastMonthPaid(lotFound);

        LotDTO lotDTO = lotService.convertToDTO(lotFound);
        lotDTO.setPayment(CommonValues.DEFAULT_COST);

        return lotDTO;
    }

    @Secured("ROLE_STAFF")
    @PostMapping(value = "/" + OBJECT_NAME + "/payment", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public LotDTO applyPayment(@RequestBody LotDTO lotDTO) throws Exception {
        if (null == lotDTO || null == lotDTO.getId() || lotDTO.getReceiptId().isEmpty()) {
            throw new Exception("Invalid Lot");
        }

        Optional<Lot> lot = lotService.findById(lotDTO.getId());
        if (lot.isEmpty()) {
            throw new Exception("Lot not found");
        }

        Lot lotFound = lot.get();
        addMonthToLastMonthPaid(lotFound);
        lotService.save(lotFound);

        LotReceipt lotReceipt = new LotReceipt();
        lotReceipt.setLot(lotFound);
        lotReceipt.setCost(CommonValues.DEFAULT_COST);
        lotReceipt.setMonthPaid(lotFound.getLastMonthPaid());
        lotReceipt.setReceiptId(lotDTO.getReceiptId());

        lotReceiptService.save(lotReceipt);

        return lotDTO;
    }

    private void addMonthToLastMonthPaid(Lot lot) {
        int monthsToPay = 1;

        Date lastMonthPaid = lot.getLastMonthPaid();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastMonthPaid);
        calendar.add(Calendar.MONTH, monthsToPay);

        lot.setLastMonthPaid(calendar.getTime());
    }
}
