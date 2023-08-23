package com.villaalegre.asada.Controllers;

import com.villaalegre.asada.DTO.LotDTO;
import com.villaalegre.asada.Models.Lot;
import com.villaalegre.asada.Models.Type;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Services.LotService;
import com.villaalegre.asada.Services.TypeService;
import com.villaalegre.asada.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    @Autowired
    private LotService lotService;

    @Autowired
    private UserService userService;

    @Autowired
    private TypeService typeService;

    @Secured("ROLE_STAFF")
    @GetMapping(value = "/lots/{userId}")
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

    @Secured("ROLE_STAFF")
    @GetMapping(value = "/lot/{lotId}")
    @ResponseBody
    public LotDTO getLot(@PathVariable(value = "lotId") Long lotId) throws Exception {
        Optional<Lot> lot = lotService.findById(lotId);

        if (lot.isEmpty()) {
            throw new Exception("Lot not found");
        }

        return lotService.convertToDTO(lot.get());
    }

    @Secured("ROLE_STAFF")
    @PostMapping(value = "/lot", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public LotDTO saveLot(@RequestBody LotDTO lotDTO) throws Exception {
        return lotService.registerNewLotOrUpdate(lotDTO);
    }

    @Secured("ROLE_STAFF")
    @PostMapping(value = "/lot/payment/{lotId}")
    @ResponseBody
    public LotDTO payMonth(@PathVariable(value = "lotId") Long lotId) throws Exception {
        final int MONTHS_TO_PAY = 1;

        Optional<Lot> lot = lotService.findById(lotId);
        if (lot.isEmpty()) {
            throw new Exception("Lot not found");
        }

        Lot lotFound = lot.get();
        Date lastMonthPaid = lotFound.getLastMonthPaid();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(lastMonthPaid);
        calendar.add(Calendar.MONTH, MONTHS_TO_PAY);

        lotFound.setLastMonthPaid(calendar.getTime());

        lotService.save(lotFound);
        return lotService.convertToDTO(lotFound);
    }
}
