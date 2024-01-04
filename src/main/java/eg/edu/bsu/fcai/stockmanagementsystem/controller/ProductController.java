package eg.edu.bsu.fcai.stockmanagementsystem.controller;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.Form;
import eg.edu.bsu.fcai.stockmanagementsystem.assets.FormService;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.ProductService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.StocksService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("products")
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
public class ProductController {
    private final ProductService productService;
    private final FormService formService;
    private final StocksService stocksService;

    @GetMapping
    public String getProductsPage(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("currentUser", getPrincipal());
        return "show-products";
    }

    @GetMapping(value = "add")
    public String getAddProductPage(Model model) {
        model.addAttribute("stocks", List.of(stocksService.findById(1L), stocksService.findById(3L)));
        model.addAttribute("currentUser", getPrincipal());
        return "add-product";
    }

    @PostMapping(value = "add")
    public String addProduct(@ModelAttribute Form form) {
        productService.add(formService.toProduct(form));
        return "redirect:/products";
    }

    @GetMapping(value = "edit/{id}")
    public String getEditProductPage(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("stocks", List.of(stocksService.findById(1L), stocksService.findById(3L)));
        model.addAttribute("currentUser", getPrincipal());
        return "edit-product";
    }

    @PostMapping(value = "edit")
    public String editProduct(@ModelAttribute Form form) {
        productService.add(formService.toProduct(form));
        return "redirect:/products";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id, Model model) {
        try {
            productService.deleteById(id);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return getProductsPage(model);
        }
        return "redirect:/products";
    }

    private ApplicationUser getPrincipal() {
        return (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

