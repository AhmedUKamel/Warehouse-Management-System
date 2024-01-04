package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old;

import eg.edu.bsu.fcai.stockmanagementsystem.model.OldPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.OldPutPermissionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OldPutPermissionService {
    private final OldPutPermissionsRepository repository;

    public OldPutPermission add(OldPutPermission permissions) {
        return repository.save(permissions);
    }

    public OldPutPermission findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
