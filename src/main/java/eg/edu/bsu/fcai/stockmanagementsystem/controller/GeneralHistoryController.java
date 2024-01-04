package eg.edu.bsu.fcai.stockmanagementsystem.controller;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "history")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN')")
public class GeneralHistoryController {
    private final HistoryService historyService;

    @GetMapping(value = "/put/main")
    public String getMainPutOperations(Model model) {
        model.addAttribute("isMainPut", true);
        model.addAttribute("currentUser", getPrincipal());
        historyService.getMainStockPuts(model);
        return "permission-history-view";
    }

    @GetMapping(value = "/put/consumed")
    public String getConsumedPutOperations(Model model) {
        model.addAttribute("isConsumedPut", true);
        model.addAttribute("currentUser", getPrincipal());
        historyService.getConsumedStockPuts(model);
        return "permission-history-view";
    }

    @GetMapping(value = "/get/main")
    public String getMainGetOperations(Model model) {
        model.addAttribute("isMainGet", true);
        model.addAttribute("currentUser", getPrincipal());
        historyService.getMainStockGets(model);
        return "permission-history-view";
    }

    @GetMapping(value = "/get/consumed")
    public String getConsumedGetOperations(Model model) {
        model.addAttribute("isConsumedGet", true);
        model.addAttribute("currentUser", getPrincipal());
        historyService.getConsumedStockGets(model);
        return "permission-history-view";
    }

    @GetMapping(value = "/get/old")
    public String getOldGetOperations(Model model) {
        model.addAttribute("isOldGet", true);
        model.addAttribute("currentUser", getPrincipal());
        historyService.getOldExtractions(model);
        return "permission-history-view";
    }

    @GetMapping(value = "/return/main")
    public String getMainReturnOperations(Model model) {
        model.addAttribute("isMainReturn", true);
        model.addAttribute("currentUser", getPrincipal());
        historyService.getMainStockReturns(model);
        return "permission-history-view";
    }

    @GetMapping(value = "/return/old")
    public String getOldReturnOperations(Model model) {
        model.addAttribute("isOldReturn", true);
        model.addAttribute("currentUser", getPrincipal());
        historyService.getOldStockReturns(model);
        return "permission-history-view";
    }

    private ApplicationUser getPrincipal() {
        return (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
