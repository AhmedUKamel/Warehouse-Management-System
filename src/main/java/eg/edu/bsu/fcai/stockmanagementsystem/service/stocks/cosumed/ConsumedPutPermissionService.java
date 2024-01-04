package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.ConsumedPutPermissionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumedPutPermissionService {
    private final ConsumedPutPermissionsRepository repository;

    public ConsumedPutPermission add(ConsumedPutPermission permission) {
        return repository.save(permission);
    }

    public ConsumedPutPermission findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
