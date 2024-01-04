package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.ConsumedGetDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumedGetDetailsService {
    private final ConsumedGetDetailsRepository repository;

    public ConsumedGetDetails add(ConsumedGetDetails details) {
        return repository.save(details);
    }

    public List<ConsumedGetDetails> findAll() {
        return repository.findAll();
    }

    public List<ConsumedGetDetails> findAllByPermission(ConsumedGetPermission permission) {
        return repository.findAllByPermission(permission);
    }

    public List<ConsumedGetDetails> findAllByPermission_To(ApplicationUser applicationUser) {
        return repository.findAllByPermission_To(applicationUser);
    }
}
