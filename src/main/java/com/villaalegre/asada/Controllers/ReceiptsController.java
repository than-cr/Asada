package com.villaalegre.asada.Controllers;

import com.villaalegre.asada.DTO.LotReceiptDTO;
import com.villaalegre.asada.Models.Lot;
import com.villaalegre.asada.Models.LotReceipt;
import com.villaalegre.asada.Models.Type;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Services.LotReceiptService;
import com.villaalegre.asada.Services.LotService;
import com.villaalegre.asada.Services.TypeService;
import com.villaalegre.asada.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
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

    @Autowired
    private TypeService typeService;

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

    @PreAuthorize("hasPermission('Receipts', 'Edit receipt')")
    @GetMapping("/" + OBJECT_NAME + "/edit/{receiptId}")
    @ResponseBody
    public LotReceiptDTO editReceipt (@PathVariable(value = "receiptId") Long receiptId) throws Exception {
        Optional<LotReceipt> lotReceipt = lotReceiptService.findById(receiptId);

        if (lotReceipt.isEmpty()){
            throw new Exception("Receipt not found.");
        }

        return lotReceiptService.convertToDTO(lotReceipt.get());
    }

    @PreAuthorize("hasPermission('Receipts', 'Edit receipt')")
    @PostMapping("/" + OBJECT_NAME + "/edit")
    @ResponseBody
    public LotReceiptDTO editReceipt (@RequestBody LotReceiptDTO lotReceiptDTO) throws Exception {
        LotReceipt lotReceipt = lotReceiptService.convertToEntity(lotReceiptDTO);

        Optional<LotReceipt> lotReceiptOpt= lotReceiptService.findById(lotReceiptDTO.getId());

        if (lotReceiptOpt.isEmpty()) {
            throw new Exception("Receipt not found.");
        }

        LotReceipt lotReceiptToSave = lotReceiptOpt.get();
        lotReceiptToSave.setReceiptId(lotReceipt.getReceiptId());
        lotReceiptToSave.setMonthPaid(lotReceipt.getMonthPaid());
        lotReceiptToSave.setCost(lotReceipt.getCost());
        lotReceiptToSave.setStatus(lotReceipt.getStatus());

        lotReceiptService.save(lotReceiptToSave);

        return lotReceiptDTO;
    }

    @PreAuthorize("hasPermission('Receipts', 'Delete receipt')")
    @PostMapping("/" + OBJECT_NAME + "/delete")
    @ResponseBody
    public LotReceiptDTO deleteReceipt (@RequestBody LotReceiptDTO lotReceiptDTO) throws Exception {
        Optional<LotReceipt> lotReceipt = lotReceiptService.findById(lotReceiptDTO.getId());

        if (lotReceipt.isEmpty()) {
            throw new Exception("Receipt not found.");
        }

        LotReceipt lotReceiptToSave = lotReceipt.get();

        Type status = typeService.findByNameAndGroup("Eliminado", "receipt status");
        lotReceiptToSave.setStatus(status);

        lotReceiptService.save(lotReceiptToSave);

        return lotReceiptDTO;
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
        model.addAttribute("editReceipts", hasPrivilege("Edit receipt"));
        model.addAttribute("deleteReceipts", hasPrivilege("Delete receipt"));

        List<Type> typeServices = typeService.findByGroup("receipt status");
        typeServices.remove(typeService.findByNameAndGroup("Eliminado", "receipt status"));
        model.addAttribute("statuses", typeServices);

        return OBJECT_NAME;
    }
}

