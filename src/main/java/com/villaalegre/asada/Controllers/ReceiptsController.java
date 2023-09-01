package com.villaalegre.asada.Controllers;

import com.villaalegre.asada.Models.CustomUserDetails;
import com.villaalegre.asada.Models.Lot;
import com.villaalegre.asada.Models.LotReceipt;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Services.LotReceiptService;
import com.villaalegre.asada.Services.LotService;
import com.villaalegre.asada.Services.UserService;
import com.villaalegre.asada.Utilities.CommonMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class ReceiptsController {

    private static final String OBJECT_NAME = "receipts";

    @Autowired
    private LotReceiptService lotReceiptService;

    @Autowired
    private LotService lotService;

    @Autowired
    private CommonMethods commonMethods;

    @PreAuthorize("hasPermission('Receipts', 'View receipts')")
    @GetMapping("/" + OBJECT_NAME)
    public String index(Model model) throws Exception {

        User user = commonMethods.getLoggedUser();

        List<Lot> lots = lotService.findByUser(user);

        List<LotReceipt> lotReceipts = lotReceiptService.getAllReceiptsByUserId(user.getId());
        model.addAttribute("lots", lots);
        model.addAttribute("lotReceipts", lotReceipts);

        return "receipts";
    }
}

