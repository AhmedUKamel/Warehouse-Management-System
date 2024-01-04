package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetItems;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainPutItems;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.MainGetItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MainGetItemsService {
    private final MainGetItemsRepository repository;

    public void addItems(List<MainPutItems> items, MainGetPermission permission) {
        items.forEach(item -> repository.save(MainGetItems.builder().id(item.getId()).permission(permission).product(item.getProduct()).build()));
    }

    public List<MainGetItems> findAll() {
        return repository.findAll();
    }

    public List<MainGetItems> getItems(ApplicationUser applicationUser, Product product, Integer number) {
        List<MainGetItems> matchesUserAndProduct = repository.findAllByProductAndPermission_To(product, applicationUser);
        List<MainGetItems> items = matchesUserAndProduct.subList(0, number);
        repository.deleteAll(items);
        return items;
    }

    public Map<Product, Integer> getProductsMap(ApplicationUser applicationUser) {
        Map<Product, Integer> map = new HashMap<>();
        repository.findAllByPermission_To(applicationUser).forEach(item -> map.merge(item.getProduct(), 1, Integer::sum));
        return map;
    }


    public List<StockVolume> getRecords() {
        return repository.getRecords();
    }

    public List<ApplicationUser> getGivenUsers() {
        return repository.getGivenUsers();
    }

}
