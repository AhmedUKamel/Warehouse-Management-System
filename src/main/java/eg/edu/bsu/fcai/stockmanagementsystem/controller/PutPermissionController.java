package eg.edu.bsu.fcai.stockmanagementsystem.controller;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.Form;
import eg.edu.bsu.fcai.stockmanagementsystem.assets.FormService;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Stock;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.*;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedPutItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedPutPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/permission/put")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN', 'USER')")
public class PutPermissionController {
    private final FormService formService;
    private final UserService userService;
    private final ItemStatusService statusService;
    private final StocksService stocksService;
    private final ProductService productService;
    private final TypeService typeService;
    private final MainPutPermissionService mainPutPermissionService;
    private final MainPutItemsService mainPutItemsService;
    private final MainPutDetailsService mainPutDetailsService;
    private final ConsumedPutPermissionService consumedPutPermissionService;
    private final ConsumedPutDetailsService consumedPutDetailsService;
    private final ConsumedPutItemsService consumedPutItemsService;
    private final List<Form> details = new ArrayList<>();
    private Stock stock;
    private Double totalPrice = .0;

    @GetMapping("/select-stock")
    public String getSelectStockPage(Model model) {
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("stocks", List.of(stocksService.findById(1L), stocksService.findById(3L)));
        model.addAttribute("heading", "أختيار مخزن الأضافة");
        model.addAttribute("title", "أضافة أذن أضافة");
        model.addAttribute("action", "/permission/put/select-stock");
        return "select-stock";
    }

    @PostMapping("/select-stock")
    public String postStock(@ModelAttribute Form form) {
        stock = stocksService.findById(form.getStockId());
        details.clear();
        totalPrice = .0;
        return "redirect:/permission/put/new";
    }

    @GetMapping(value = "/new")
    public String getNewPutPermissionPage(Model model) {
        if (stock == null) {
            return "redirect:/permission/put/select-stock";
        }
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("users", userService.findAllEnabledUsers());
        model.addAttribute("stock", stock);
        model.addAttribute("detailsList", details);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("documents", typeService.findAll().subList(0, 3));
        return "put-permission";
    }

    @PostMapping(value = "/new")
    public String postNewPutPermission(@ModelAttribute Form form, Model model) {
        completeFormDetails(form);
        if (details.size() > 0) {
            if (stock.getId().equals(1L)) {
                MainPutPermission permission = mainPutPermissionService.add(formService.toMainPutPermissions(form));
                for (var detail : details) {
                    mainPutDetailsService.add(formService.toMainPutDetails(detail, permission));
                    mainPutItemsService.addItems(permission, detail.getProduct(), detail.getNumber());
                }
                stock = null;
                return "redirect:/stocks/main";
            } else if (stock.getId().equals(3L)) {
                ConsumedPutPermission permission = consumedPutPermissionService.add(formService.toConsumedPutPermissions(form));
                for (var detail : details) {
                    consumedPutDetailsService.add(formService.toConsumedPutDetails(detail, permission));
                    consumedPutItemsService.addItems(permission, detail.getProduct(), detail.getNumber());
                }
                stock = null;
                return "redirect:/stocks/consumed";
            }
        }
        model.addAttribute("error", "تفاصيل الأضافة فارغة");
        return getNewPutPermissionPage(model);
    }

    private void completeFormDetails(Form form) {
        ApplicationUser currentApplicationUser = getPrincipal();
        form.setStockId(stock.getId());
        form.setWriterId(currentApplicationUser.getId());
        form.setTotalPrice(totalPrice);
    }

    @GetMapping(value = "/details/add")
    public String getPutDetailsPage(Model model) {
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("products", productService.findAllByStock(stock));
        model.addAttribute("allStatus", statusService.findAll().subList(0, 2));
        return "put-details";
    }

    @PostMapping(value = "/details/add")
    public String postPutDetails(@ModelAttribute Form form) {
        double detailsPrice = form.getUnitPrice() * form.getNumber();
        totalPrice += detailsPrice;
        form.setTotalPrice(detailsPrice);
        form.setProduct(productService.findById(form.getProductId()));
        form.setStatus(statusService.findById(form.getStatusId()));
        this.details.add(form);
        return "redirect:/permission/put/new";
    }

    @GetMapping(value = "/details/delete/{index}")
    public String deletePutDetails(@PathVariable(name = "index") Integer index) {
        Form details = this.details.get(index);
        totalPrice -= details.getTotalPrice();
        this.details.remove(details);
        return "redirect:/permission/put/new";
    }

    @GetMapping("/new/clear")
    public String clearAndGetNewPutPermissionPage() {
        if (stock == null) {
            return "redirect:/permission/put/select-stock";
        }
        details.clear();
        totalPrice = .0;
        return "redirect:/permission/put/select-stock";
    }

    private ApplicationUser getPrincipal() {
        return (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
