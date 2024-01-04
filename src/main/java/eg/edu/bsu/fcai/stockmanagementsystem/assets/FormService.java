package eg.edu.bsu.fcai.stockmanagementsystem.assets;

import eg.edu.bsu.fcai.stockmanagementsystem.model.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Stock;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class FormService {
    private final ProductService productService;
    private final ItemStatusService itemStatusService;
    private final UserService userService;
    private final StocksService stocksService;
    private final TypeService typeService;

    public MainPutDetails toMainPutDetails(Form form, MainPutPermission permission) {
        return MainPutDetails.builder().permission(permission).product(productService.findById(form.getProductId())).number(form.getNumber()).unitPrice(form.getUnitPrice()).totalPrice(form.getTotalPrice()).status(form.getStatus()).note(form.getNote()).build();
    }

    public ConsumedPutPermission toConsumedPutPermissions(Form form) {
        Stock stock = stocksService.findById(form.getStockId());
        return ConsumedPutPermission.builder().from(userService.findById(form.getUserId())).groupNumber(form.getNumber()).to(stock).date(new Date()).type(typeService.findById(form.getDocumentId())).groupNumber(form.getGroup()).writer(userService.findById(form.getWriterId())).note(form.getNote()).price(form.getTotalPrice()).stockAdmin(stock.getAdmin()).build();
    }

    public ConsumedPutDetails toConsumedPutDetails(Form form, ConsumedPutPermission permission) {
        return ConsumedPutDetails.builder().permission(permission).product(productService.findById(form.getProductId())).number(form.getNumber()).unitPrice(form.getUnitPrice()).totalPrice(form.getTotalPrice()).status(form.getStatus()).note(form.getNote()).build();
    }

    public MainGetPermission toMainGetPermissions(Form form) {
        Stock stock = stocksService.findById(form.getStockId());
        return MainGetPermission.builder().to(userService.findById(form.getUserId())).from(stock).stockAdmin(stock.getAdmin()).date(new Date()).writer(userService.findById(form.getWriterId())).note(form.getNote()).build();
    }

    public MainGetDetails toMainGetDetails(Form form, MainGetPermission permission) {
        return MainGetDetails.builder().permission(permission).product(form.getProduct()).number(form.getNumber()).status(itemStatusService.findById(3L)).note(form.getNote()).build();
    }

    public ConsumedGetPermission toConsumedGetPermissions(Form form) {
        Stock stock = stocksService.findById(form.getStockId());
        return ConsumedGetPermission.builder().to(userService.findById(form.getUserId())).from(stock).stockAdmin(stock.getAdmin()).date(new Date()).writer(userService.findById(form.getWriterId())).note(form.getNote()).build();
    }

    public ConsumedGetDetails toConsumedGetDetails(Form form, ConsumedGetPermission permissions) {
        return ConsumedGetDetails.builder().permission(permissions).product(form.getProduct()).number(form.getNumber()).status(itemStatusService.findById(3L)).note(form.getNote()).build();
    }

    public MainPutPermission toMainPutPermissions(Form form) {
        Stock stock = stocksService.findById(form.getStockId());
        return MainPutPermission.builder().from(userService.findById(form.getUserId())).price(form.getTotalPrice()).to(stock).stockAdmin(stock.getAdmin()).date(new Date()).type(typeService.findById(form.getDocumentId())).writer(userService.findById(form.getWriterId())).groupNumber(form.getGroup()).note(form.getNote()).build();
    }


    public OldPutPermission toOldPutPermissions(Form form) {
        Stock stock = stocksService.findById(form.getStockId());
        return OldPutPermission.builder().from(userService.findById(form.getUserId())).to(stock).stockAdmin(stock.getAdmin()).date(new Date()).type(typeService.findById(form.getDocumentId())).writer(userService.findById(form.getWriterId())).note(form.getNote()).build();
    }

    public OldPutDetails toOldPutDetails(Form form, OldPutPermission permission) {
        return OldPutDetails.builder().permission(permission).product(productService.findById(form.getProductId())).number(form.getNumber()).status(form.getStatus()).note(form.getNote()).build();
    }

    public OldGetPermission toOldGetPermission(Form form) {
        Stock stock = stocksService.findById(form.getStockId());
        return OldGetPermission.builder().from(stock).stockAdmin(stock.getAdmin()).date(new Date()).writer(userService.findById(form.getWriterId())).note(form.getNote()).build();
    }

    public OldGetDetails toOldGetDetails(Form form, OldGetPermission permission) {
        return OldGetDetails.builder().permission(permission).product(form.getProduct()).number(form.getNumber()).build();
    }

    public Product toProduct(Form form) {
        Stock stock = stocksService.findById(form.getStockId());
        return Product.builder().id(form.getProductId()).name(form.getUserId()).monthsValidation(form.getNumber()).stock(stock).build();
    }
}
