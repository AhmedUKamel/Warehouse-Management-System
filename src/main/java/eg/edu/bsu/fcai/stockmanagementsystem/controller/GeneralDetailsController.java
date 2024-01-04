package eg.edu.bsu.fcai.stockmanagementsystem.controller;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.DetailsParent;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedGetPermissionsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed.ConsumedPutPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainPutPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main.MainGetPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldGetDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldGetPermissionService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldPutDetailsService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old.OldPutPermissionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/details")
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
public class GeneralDetailsController extends DetailsParent {
    public GeneralDetailsController(MainPutDetailsService mainPutDetailsService,
                                    MainPutPermissionService mainPutPermissionService,
                                    MainGetPermissionService mainGetPermissionService,
                                    MainGetDetailsService mainGetDetailsService,
                                    OldGetPermissionService oldGetPermissionService,
                                    OldGetDetailsService oldGetDetailsService,
                                    OldPutPermissionService oldPutPermissionService,
                                    OldPutDetailsService oldPutDetailsService,
                                    ConsumedPutPermissionService consumedPutPermissionService,
                                    ConsumedPutDetailsService consumedPutDetailsService,
                                    ConsumedGetPermissionsService consumedGetPermissionsService,
                                    ConsumedGetDetailsService consumedGetDetailsService) {
        super(mainPutDetailsService,
                mainPutPermissionService,
                mainGetPermissionService,
                mainGetDetailsService,
                oldGetPermissionService,
                oldGetDetailsService,
                oldPutPermissionService,
                oldPutDetailsService,
                consumedPutPermissionService,
                consumedPutDetailsService,
                consumedGetPermissionsService,
                consumedGetDetailsService);
    }

    @GetMapping(value = "/main/put/{id}")
    public String getMainPutDetails(@PathVariable(name = "id") Long id, Model model) {
        MainPutPermission permission = mainPutPermissionService.findById(id);
        model.addAttribute("isPut", true);
        model.addAttribute("mainPuts", mainPutDetailsService.findByPermission(permission));
        model.addAttribute("mainPutPermission", permission);
        model.addAttribute("currentUser", getPrincipal());
        return "details";

    }

    @GetMapping(value = "/consumed/put/{id}")
    public String getConsumedPutDetails(@PathVariable(name = "id") Long id, Model model) {
        ConsumedPutPermission permission = consumedPutPermissionService.findById(id);
        model.addAttribute("isPut", true);
        model.addAttribute("mainPuts", consumedPutDetailsService.findAllByPermission(permission));
        model.addAttribute("mainPutPermission", permission);
        model.addAttribute("currentUser", getPrincipal());
        return "details";
    }

    @GetMapping(value = "/main/get/{id}")
    public String getMainGetDetails(@PathVariable(name = "id") Long id, Model model) {
        MainGetPermission permission = mainGetPermissionService.findById(id);
        model.addAttribute("isGet", true);
        model.addAttribute("mainGets", mainGetDetailsService.findAllByPermission(permission));
        model.addAttribute("mainGetPermission", permission);
        model.addAttribute("currentUser", getPrincipal());
        return "/details";
    }

    @GetMapping(value = "/consumed/get/{id}")
    public String getConsumedGetDetails(@PathVariable(name = "id") Long id, Model model) {
        ConsumedGetPermission permission = consumedGetPermissionsService.findById(id);
        model.addAttribute("isGet", true);
        model.addAttribute("mainGets", consumedGetDetailsService.findAllByPermission(permission));
        model.addAttribute("mainGetPermission", permission);
        model.addAttribute("currentUser", getPrincipal());
        return "details";
    }

    @GetMapping(value = "/old/get/{id}")
    public String getExtractionDetailsINfo(@PathVariable(name = "id") Long id, Model model) {
        OldGetPermission permission = oldGetPermissionService.findById(id);
        model.addAttribute("oldGets", oldGetDetailsService.findAllByPermission(permission));
        model.addAttribute("oldGetPermission", permission);
        model.addAttribute("isExtraction", true);
        model.addAttribute("currentUser", getPrincipal());
        return "details";
    }

    @GetMapping(value = "/main/return/{id}")
    public String getMainReturnDetails(@PathVariable(name = "id") Long id, Model model) {
        MainPutPermission permission = mainPutPermissionService.findById(id);
        model.addAttribute("isReturn", true);
        model.addAttribute("mainReturns", mainPutDetailsService.findByPermission(permission));
        model.addAttribute("mainReturnPermission", permission);
        model.addAttribute("currentUser", getPrincipal());
        return "details";
    }

    @GetMapping(value = "/old/return/{id}")
    public String getOldReturnDetails(@PathVariable(name = "id") Long id, Model model) {
        OldPutPermission permission = oldPutPermissionService.findById(id);
        model.addAttribute("isReturn", true);
        model.addAttribute("mainReturns", oldPutDetailsService.findByPermission(permission));
        model.addAttribute("mainReturnPermission", permission);
        model.addAttribute("currentUser", getPrincipal());
        return "details";
    }

    private ApplicationUser getPrincipal() {
        return (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
