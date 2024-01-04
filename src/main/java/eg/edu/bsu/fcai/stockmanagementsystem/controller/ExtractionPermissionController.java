package eg.edu.bsu.fcai.stockmanagementsystem.controller;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.Form;
import eg.edu.bsu.fcai.stockmanagementsystem.assets.FormService;
import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Stock;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.ProductService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.StocksService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldGetPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldPutItemsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "permission/extraction")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN', 'USER')")
public class ExtractionPermissionController {
    private final OldPutItemsService oldPutItemsService;
    private final OldGetDetailsService oldGetDetailsService;
    private final OldGetPermissionService service;
    private final FormService formService;
    private final StocksService stocksService;
    private final ProductService productService;
    private List<StockVolume> items;
    private final List<Form> details = new ArrayList<>();

    @GetMapping(value = "new/clear")
    public String clearAndGetNewExtractionPermissionPage() {
        details.clear();
        items = new ArrayList<>(oldPutItemsService.getRecords());
        return "redirect:/permission/extraction/new";
    }

    @GetMapping(value = "new")
    public String getNewExtractionPermissionPage(Model model) {
        if (items == null) {
            return "redirect:/permission/extraction/new/clear";
        }
        model.addAttribute("currentUser", getPrincipal());
        model.addAttribute("stock", stocksService.findById(2L));
        model.addAttribute("details", details);
        model.addAttribute("items", items);
        return "extraction-permission";
    }


    @PostMapping(value = "new")
    public String postExtractionPermission(@ModelAttribute Form form, Model model) {
        completeFormDetails(form);
        if (details.size() > 0) {
            OldGetPermission permission = service.add(formService.toOldGetPermission(form));
            for (var detail : details) {
                oldGetDetailsService.add(formService.toOldGetDetails(detail, permission));
                oldPutItemsService.deleteItems(detail.getProduct(), detail.getNumber());
            }
            items = null;
            return "redirect:/stocks/old";
        }
        model.addAttribute("error", "تفاصيل الأتلاف فارغة");
        return getNewExtractionPermissionPage(model);
    }

    private void completeFormDetails(Form form) {
        ApplicationUser currentApplicationUser = getPrincipal();
        Stock stock = stocksService.findById(2L);
        form.setStockId(stock.getId());
        form.setWriterId(currentApplicationUser.getId());
    }

    @GetMapping(value = "{id}")
    public String getExtractionDetailsPage(@PathVariable(name = "id") Long id, Model model) {
        Product product = productService.findById(id);
        var stream = items.stream().filter(i -> i.getProduct().equals(product)).findFirst();
        if (stream.isPresent()) {
            Long max = stream.get().getNumber();
            model.addAttribute("selectedProduct", product);
            model.addAttribute("max", max);
            model.addAttribute("currentUser", getPrincipal());
            model.addAttribute("stock", stocksService.findById(2L));
            model.addAttribute("details", details);
            model.addAttribute("items", items);
        }
        return "extraction-details";
    }

    @PostMapping(value = "details/add")
    public String postExtractionDetails(@ModelAttribute Form form) {
        Product product = productService.findById(form.getProductId());
        form.setProduct(product);
        for (var i : items) {
            if (i.getProduct().equals(product)) {
                i.decrementBy(form.getNumber().longValue());
                if (i.getNumber() == 0) {
                    items.remove(i);
                }
                break;
            }
        }
        details.add(form);
        return "redirect:/permission/extraction/new";
    }

    @GetMapping(value = "details/delete/{index}")
    public String deleteExtractionDetails(@PathVariable(name = "index") Integer index) {
        Form details = this.details.get(index);
        this.details.remove(details);
        Product product = details.getProduct();
        for (var i : items) {
            if (i.getProduct().equals(product)) {
                i.incrementBy(details.getNumber().longValue());
                break;
            }
        }
        return "redirect:/permission/extraction/new";
    }
    private ApplicationUser getPrincipal() {
        return (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
