package eg.edu.bsu.fcai.stockmanagementsystem.controller;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.UserForm;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.service.HistoryService;
import eg.edu.bsu.fcai.stockmanagementsystem.service.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("profile")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('SUPER_ADMIN', 'ADMIN', 'USER', 'CONSUMER')")
public class ProfileController {
    private final UpdateUserService updateUserService;
    private final HistoryService historyService;

    @GetMapping
    public String getUserProfilePage(Model model) {
        model.addAttribute("currentUser", getPrincipal());
        return "profile";
    }

    private ApplicationUser getPrincipal() {
        return (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @GetMapping("edit")
    public String getEditUserPage(Model model) {
        ApplicationUser applicationUser = getPrincipal();
        model.addAttribute("currentUser", applicationUser);
        model.addAttribute("user", applicationUser);
        model.addAttribute("action", "/profile/edit");
        return "profile-edit";
    }

    @PostMapping("edit")
    public String postUserUpdates(@ModelAttribute UserForm form, Model model) {
        try {
            updateUserService.updateUser(form, true);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return getEditUserPage(model);
        }
        return "redirect:/profile";
    }

    @GetMapping("usage")
    public String getPersonalUsage(Model model) {
        ApplicationUser applicationUser = getPrincipal();
        historyService.getHistoryForUser(model, applicationUser);
        model.addAttribute("currentUser", applicationUser);
        return "history";
    }
}
