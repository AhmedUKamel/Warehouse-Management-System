package eg.edu.bsu.fcai.stockmanagementsystem.service.entities;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ItemStatus;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.ItemStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemStatusService {
    private final ItemStatusRepository repository;

    public ItemStatus add(ItemStatus itemStatus) {
        return repository.save(itemStatus);
    }

    public ItemStatus findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<ItemStatus> findAll() {
        return repository.findAll();
    }
}
