package com.villaalegre.asada.Controllers;

import com.sun.security.auth.UserPrincipal;
import com.villaalegre.asada.DTO.LotDTO;
import com.villaalegre.asada.Models.Lot;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Services.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController extends AbstractController{

    @Autowired
    private LotService lotService;

    @GetMapping("/")
    public String index()
    {
        return "redirect:/login";
    }

    @PreAuthorize("hasPermission('Home', 'View home')")
    @GetMapping("/home")
    public String home(Model model) {
        User user = getLoggedUser();
        List<Lot> lots = lotService.findByUser(user);

        List<LotDTO> lotsDto = new ArrayList<>();
        for (Lot lot: lots) {
            lotsDto.add(lotService.convertToDTO(lot));
            int lastLotAddIndex = lotsDto.size() - 1;
            String lastMonthPaid = lotsDto.get(lastLotAddIndex).getLastMonthPaid();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String today = simpleDateFormat.format(new Date());

            Temporal lastMonthPaidTmp = LocalDate.parse(lastMonthPaid).withDayOfMonth(1);
            Temporal todayTmp = LocalDate.parse(today).withDayOfMonth(1);
            long monthsBetween = ChronoUnit.MONTHS.between(lastMonthPaidTmp, todayTmp);

            lotsDto.get(lotsDto.size() - 1).setMonthsInDebt(monthsBetween);
        }

        model.addAttribute("lots", lotsDto);
        model.addAttribute("lotsQuantity", lots.size());
        return "home";
    }
}
