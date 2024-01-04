package eg.edu.bsu.fcai.stockmanagementsystem.controller;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ItemStatus;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Stock;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Type;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static eg.edu.bsu.fcai.stockmanagementsystem.model.UserRole.*;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class InitializationController {
    private final UserService userService;
    private final ItemStatusService itemStatusService;
    private final TypeService typeService;
    private final StocksService stocksService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/init")
    public String initializeDatabaseRecords() {
        if (userService.count() <= 0) {
            ApplicationUser superAdmin = ApplicationUser.builder().id("00000000000000").email("superadmin@stock.com").password(passwordEncoder.encode("superadmin")).nameEnglish("Ahmed Hatem").nameArabic("أحمد حاتم محمدلطفي").phone("01234567890").imageUrl("/assets/img/logo.png").isEnabled(true).isAccountNonExpired(true).isCredentialsNonExpired(true).isAccountNonLocked(true).authorities(SUPER_ADMIN.getGrantedAuthorities()).build();
            userService.add(superAdmin);

            itemStatusService.add(ItemStatus.builder().name("جديد").build());
            itemStatusService.add(ItemStatus.builder().name("مستعمل").build());
            itemStatusService.add(ItemStatus.builder().name("مسترجع").build());
            itemStatusService.add(ItemStatus.builder().name("كهنة").build());

            Type pd = Type.builder().name("أضافة من الجامعة").build();
            typeService.add(pd);
            pd = Type.builder().name("هدية").build();
            typeService.add(pd);
            pd = Type.builder().name("فاتورة").build();
            typeService.add(pd);
            pd = Type.builder().name("أسترجاع").build();
            typeService.add(pd);

            Stock stock1 = Stock.builder().name("المستديم").admin(superAdmin).build();
            stocksService.add(stock1);
            Stock stock2 = Stock.builder().name("الكهنة").admin(superAdmin).build();
            stocksService.add(stock2);
            Stock stock3 = Stock.builder().name("المستهلك").admin(superAdmin).build();
            stocksService.add(stock3);
        }
        return "redirect:/";
    }
}
