package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.MainGetDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainGetDetailsService {
    private final MainGetDetailsRepository repository;

    public MainGetDetails add(MainGetDetails permission) {
        return repository.save(permission);
    }

    public MainGetDetails findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<MainGetDetails> findAll() {
        return repository.findAll();
    }

    public List<MainGetDetails> findAllByPermission(MainGetPermission permission) {
        return repository.findAllByPermission(permission);
    }

    public List<MainGetDetails> findAllByUser(ApplicationUser applicationUser) {
        return repository.findAllByPermission_To(applicationUser);
    }
}
