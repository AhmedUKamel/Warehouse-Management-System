package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetItems;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldPutItems;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.OldPutItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OldPutItemsService {
    private final OldPutItemsRepository repository;

    public void addItems(List<MainGetItems> items, OldPutPermission permission) {
        items.forEach(item -> repository.save(OldPutItems.builder().id(item.getId()).product(item.getProduct()).permission(permission).build()));
    }

    public List<StockVolume> getRecords() {
        return repository.getRecords();
    }

    public void deleteItems(Product product, Integer number) {
        repository.deleteAll(repository.findAllByProduct(product).subList(0, number));
    }
}
