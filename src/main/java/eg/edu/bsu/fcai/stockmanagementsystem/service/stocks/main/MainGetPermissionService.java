package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.MainGetPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainGetPermissionService {
    private final MainGetPermissionRepository repository;

    public MainGetPermission add(MainGetPermission permission) {
        return repository.save(permission);
    }

    public MainGetPermission findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<MainGetPermission> findAll() {
        return repository.findAll();
    }

    public long count() {
        return repository.count();
    }
}
