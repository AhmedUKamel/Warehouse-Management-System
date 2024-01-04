package eg.edu.bsu.fcai.stockmanagementsystem.controller;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedGetItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedPutItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainGetItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldPutItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "stocks")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN', 'USER')")
public class StocksController {
    private final MainPutItemsService mainPutItemsService;
    private final MainGetItemsService mainGetItemsService;
    private final ConsumedPutItemsService consumedPutItemsService;
    private final ConsumedGetItemsService consumedGetItemsService;
    private final OldPutItemsService oldPutItemsService;

    @GetMapping(value = "/main")
    public String getMainStockPutPage(Model model) {
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("remain", mainPutItemsService.getRecords());
        model.addAttribute("header", "المنتجات المتاحة بمخزن المستديم");
        return "stocks";
    }

    @GetMapping(value = "/main/takeouts")
    public String getMainStockGetPage(Model model) {
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("remain", mainGetItemsService.getRecords());
        model.addAttribute("header", "المنتجات المصروفة من مخزن المستديم");
        return "stocks";
    }

    @GetMapping(value = "/consumed")
    public String getConsumedStockPutPage(Model model) {
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("remain", consumedPutItemsService.getRecords());
        model.addAttribute("header", "المنتجات المتاحة بمخزن المستهلك");
        return "stocks";
    }

    @GetMapping(value = "/consumed/takeouts")
    public String getConsumedStockGetPage(Model model) {
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("remain", consumedGetItemsService.getRecords());
        model.addAttribute("header", "المنتجات المصروفة من مخزن المستهلك");
        return "stocks";
    }

    @GetMapping(value = "/old")
    public String getOldStockPage(Model model) {
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("remain", oldPutItemsService.getRecords());
        model.addAttribute("header", "المنتجات المتاحة بمخزن الكهنة");
        return "stocks";
    }
    private ApplicationUser getPrincipal() {
        return (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
