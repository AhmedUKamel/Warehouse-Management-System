package eg.edu.bsu.fcai.stockmanagementsystem.service.stocks.old;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldPutPermission;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.OldPutDetailsRepository;
import eg.edu.bsu.fcai.stockmanagementsystem.service.entities.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OldPutDetailsService {
    private final OldPutDetailsRepository repository;
    private final TypeService documentService;

    public OldPutDetails add(OldPutDetails details) {
        return repository.save(details);
    }

    public List<OldPutDetails> findAllReturns() {
        return repository.findByPermission_Type(documentService.findById(4L));
    }

    public List<OldPutDetails> findByPermission(OldPutPermission permission) {
        return repository.findByPermission(permission);
    }

    public List<OldPutDetails> findByPermission_From(ApplicationUser applicationUser) {
        return repository.findByPermission_From(applicationUser);
    }
}
