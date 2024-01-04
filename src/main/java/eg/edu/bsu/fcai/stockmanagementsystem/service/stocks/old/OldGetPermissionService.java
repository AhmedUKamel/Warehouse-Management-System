package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old;

import eg.edu.bsu.fcai.stockmanagementsystem.model.OldGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.OldGetPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OldGetPermissionService {
    private final OldGetPermissionRepository repository;

    public OldGetPermission add(OldGetPermission permission) {
        return repository.save(permission);
    }

    public OldGetPermission findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public long count() {
        return repository.count();
    }
}
