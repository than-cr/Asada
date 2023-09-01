package com.villaalegre.asada.Controllers;

import com.villaalegre.asada.Models.Lot;
import com.villaalegre.asada.Models.LotReceipt;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Services.LotReceiptService;
import com.villaalegre.asada.Services.LotService;
import com.villaalegre.asada.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ReceiptsController extends AbstractController{

    private static final String OBJECT_NAME = "receipts";

    @Autowired
    private LotReceiptService lotReceiptService;

    @Autowired
    private LotService lotService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasPermission('Receipts', 'View receipts')")
    @GetMapping("/" + OBJECT_NAME)
    public String index(Model model) throws Exception {
        return showReceiptsHistory(model, "");
    }

    @PreAuthorize("hasPermission('Receipts', 'View all receipts')")
    @GetMapping("/" + OBJECT_NAME + "/{username}")
    public String indexPerUser(Model model, @PathVariable(value = "username") String username) throws Exception {
        return showReceiptsHistory(model, username);
    }

    private String showReceiptsHistory(Model model, String username) throws Exception {
        User user = getLoggedUser();
        if (!username.isEmpty() && !username.isBlank())
        {
            Optional<User> optionalUser = userService.findByUsername(username);
            if (optionalUser.isEmpty()) {
                throw new Exception("User not found.");
            }

            user = optionalUser.get();
        }

        boolean viewAllReceiptsUserEnabled = hasPrivilege("View all receipts");
        if (viewAllReceiptsUserEnabled) {
            List<User> users = userService.findAll();
            model.addAttribute("userList", users);
        }

        List<Lot> lots = lotService.findByUser(user);

        List<LotReceipt> lotReceipts = lotReceiptService.getAllReceiptsByUserId(user.getId());
        model.addAttribute("viewAllReceipts", viewAllReceiptsUserEnabled);
        model.addAttribute("lots", lots);
        model.addAttribute("lotReceipts", lotReceipts);

        return OBJECT_NAME;
    }
}

