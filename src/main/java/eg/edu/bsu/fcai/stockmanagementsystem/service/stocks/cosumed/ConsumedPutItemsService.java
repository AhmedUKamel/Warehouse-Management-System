package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed;

import eg.edu.bsu.fcai.stockmanagementsystem.assets.StockVolume;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Product;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutItems;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.ConsumedPutItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumedPutItemsService {
    private final ConsumedPutItemsRepository repository;

    public void addItems(ConsumedPutPermission permission, Product product, Integer amount) {
        while (amount-- > 0) {
            repository.save(ConsumedPutItems.builder().permission(permission).product(product).build());
        }
    }

    public List<ConsumedPutItems> getItems(Product product, Integer number) {
        List<ConsumedPutItems> items = repository.findByProduct(product).subList(0, number);
        repository.deleteAll(items);
        return items;
    }

    public List<StockVolume> getRecords() {
        return repository.getRecords();
    }

}
