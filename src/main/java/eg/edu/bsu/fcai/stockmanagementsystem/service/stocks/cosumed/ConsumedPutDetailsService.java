package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.cosumed;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.ConsumedPutDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumedPutDetailsService {
    private final ConsumedPutDetailsRepository repository;

    public ConsumedPutDetails add(ConsumedPutDetails details) {
        return repository.save(details);
    }

    public List<ConsumedPutDetails> findAll() {
        return repository.findAll();
    }

    public List<ConsumedPutDetails> findAllByPermission(ConsumedPutPermission permission) {
        return repository.findAllByPermission(permission);
    }

    public List<ConsumedPutDetails> findAllByPermission_From(ApplicationUser applicationUser) {
        return repository.findAllByPermission_From(applicationUser);
    }

}
