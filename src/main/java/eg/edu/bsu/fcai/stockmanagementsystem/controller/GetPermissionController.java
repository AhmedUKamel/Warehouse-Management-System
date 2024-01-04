package eg.edu.bsu.fcai.stockmanagementsystem.controller;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.Form;
import eg.edu.bsu.fcai.stockmanagementsystem.assets.FormService;
import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Stock;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.ItemStatusService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.ProductService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.StocksService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.UserService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedGetItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedGetPermissionsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedPutItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainGetItemsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainGetPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/permission/get")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN', 'USER')")
public class GetPermissionController {
    private final UserService userService;
    private final FormService formService;
    private final ItemStatusService statusService;
    private final StocksService stocksService;
    private final ProductService productService;
    private final MainGetPermissionService mainGetPermissionService;
    private final ConsumedGetPermissionsService consumedGetPermissionsService;
    private final MainGetDetailsService mainGetDetailsService;
    private final MainPutItemsService mainPutItemsService;
    private final MainGetItemsService mainGetItemsService;
    private final ConsumedPutItemsService consumedStockService;
    private final ConsumedGetItemsService consumedGetItemsService;
    private final ConsumedGetDetailsService consumedGetDetailsService;
    private final List<Form> details = new ArrayList<>();
    private List<StockVolume> items = new ArrayList<>();
    private Stock stock;

    @GetMapping("/select-stock")
    public String getSelectStockPage(Model model) {
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("stocks", List.of(stocksService.findById(1L), stocksService.findById(3L)));
        model.addAttribute("heading", "أختيار مخزن الصرف");
        model.addAttribute("title", "أضافة أذن صرف");
        model.addAttribute("action", "/permission/get/select-stock");
        return "select-stock";
    }

    @PostMapping("/select-stock")
    public String postSelectedStock(@ModelAttribute Form form) {
        stock = stocksService.findById(form.getStockId());
        items = (stock.getId().equals(1L)) ? mainPutItemsService.getRecords() : consumedStockService.getRecords();
        details.clear();
        return "redirect:/permission/get/new";
    }

    @GetMapping(value = "/new")
    public String getNewGetPermissionPage(Model model) {
        if (stock == null) {
            return "redirect:/permission/get/select-stock";
        }
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("users", userService.findAllEnabledUsers());
        model.addAttribute("stock", stock);
        model.addAttribute("details", details);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("items", items);
        return "get-permission";
    }

    @PostMapping(value = "/new")
    public String addNewGetPermission(@ModelAttribute Form form, Model model) {
        completeFormDetails(form);
        if (details.size() > 0) {
            if (stock.getId().equals(1L)) {
                MainGetPermission permission = mainGetPermissionService.add(formService.toMainGetPermissions(form));
                for (var detail : details) {
                    mainGetDetailsService.add(formService.toMainGetDetails(detail, permission));
                    mainGetItemsService.addItems(mainPutItemsService.getItems(detail.getProduct(), detail.getNumber()), permission);
                }
                stock = null;
                return "redirect:/stocks/main/takeouts";
            } else if (stock.getId().equals(3L)) {
                ConsumedGetPermission permission = consumedGetPermissionsService.add(formService.toConsumedGetPermissions(form));
                for (var detail : details) {
                    consumedGetDetailsService.add(formService.toConsumedGetDetails(detail, permission));
                    consumedGetItemsService.addItems(consumedStockService.getItems(detail.getProduct(), detail.getNumber()), permission);
                }
                stock = null;
                return "redirect:/stocks/consumed/takeouts";
            }
        }
        model.addAttribute("error", "تفاصيل الصرف فارغة");
        return getNewGetPermissionPage(model);
    }

    private void completeFormDetails(Form form) {
        ApplicationUser currentApplicationUser = getPrincipal();
        form.setWriterId(currentApplicationUser.getId());
        form.setStockId(stock.getId());
    }

    @GetMapping(value = "/{id}")
    public String getNewGetDetailsPage(@PathVariable(name = "id") Long id, Model model) {
        if (stock == null) {
            return "redirect:/permission/get/select-stock";
        }
        Product product = productService.findById(id);
        var stream = items.stream().filter(i -> i.getProduct().equals(product)).findFirst();
        if (stream.isPresent()) {
            Long max = stream.get().getNumber();
            model.addAttribute("currentUser", getPrincipal());
            model.addAttribute("selectedProduct", product);
            model.addAttribute("allStatus", statusService.findAll().subList(0, 2));
            model.addAttribute("max", max);
        }
        return "get-details";
    }

    @PostMapping(value = "/details/add")
    public String addNewGetDetails(@ModelAttribute Form form) {
        Product product = productService.findById(form.getProductId());
        form.setProduct(product);
        form.setStatus(statusService.findById(form.getStatusId()));
        for (var item : items) {
            if (item.getProduct().equals(product)) {
                item.decrementBy(form.getNumber().longValue());
                if (item.getNumber() == 0) {
                    items.remove(item);
                }
                break;
            }
        }
        this.details.add(form);
        return "redirect:/permission/get/new";
    }

    @GetMapping(value = "/details/delete/{index}")
    public String deleteGetDetails(@PathVariable(name = "index") Integer index) {
        Form detail = this.details.get(index);
        this.details.remove(detail);
        for (var i : items) {
            if (i.getProduct().equals(detail.getProduct())) {
                i.incrementBy(detail.getNumber().longValue());
                break;
            }
        }
        return "redirect:/permission/get/new";
    }

    @GetMapping(value = "/new/clear")
    public String clearAndGetNewGetPermissionPage() {
        if (stock == null) {
            return "redirect:/permission/get/select-stock";
        }
        details.clear();
        items = (stock.getId().equals(1L)) ? mainPutItemsService.getRecords() : consumedStockService.getRecords();
        return "redirect:/permission/get/new";
    }
    private ApplicationUser getPrincipal() {
        return (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
