package eg.edu.bsu.fcai.stockmanagementsystem.service.entities;

import eg.edu.bsu.fcai.stockmanagementsystem.model.Stock;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StocksService {
    private final StockRepository repository;

    public Stock add(Stock stock) {
        return repository.save(stock);
    }

    public Stock findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Stock> findAll() {
        return repository.findAll();
    }

    public long count() {
        return repository.count();
    }
}
