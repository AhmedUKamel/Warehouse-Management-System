package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.model.MainPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.MainPutPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPutPermissionService {
    private final MainPutPermissionRepository repository;

    public MainPutPermission add(MainPutPermission permission) {
        return repository.save(permission);
    }

    public List<MainPutPermission> findAll() {
        return repository.findAll();
    }

    public MainPutPermission findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public long count() {
        return repository.count();
    }
}
