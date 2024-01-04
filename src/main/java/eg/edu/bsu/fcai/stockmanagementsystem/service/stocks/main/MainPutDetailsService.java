package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.main;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.MainPutDetailsRepository;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPutDetailsService {
    private final MainPutDetailsRepository repository;
    private final TypeService typeService;

    public MainPutDetails add(MainPutDetails details) {
        return repository.save(details);
    }

    public List<MainPutDetails> findAll() {
        return repository.findAll();
    }

    public List<MainPutDetails> findByPermission(MainPutPermission permission) {
        return repository.findByPermission(permission);
    }

    public List<MainPutDetails> findAllNewAdditionsByUser(ApplicationUser applicationUser) {
        return repository.findAllByUserAndNotPermission_Type(applicationUser, typeService.findById(4L));
    }

    public List<MainPutDetails> findAllReturnedAdditionsByUser(ApplicationUser applicationUser) {
        return repository.findAllByPermission_FromAndPermission_Type(applicationUser, typeService.findById(4L));
    }

    public List<MainPutDetails> findAllReturns() {
        return repository.findAllByPermission_Type(typeService.findById(4L));
    }

    public List<MainPutDetails> findAllAdditions() {
        return repository.findAllByNotPermission_Type(typeService.findById(4L));
    }
}
