package eg.edu.bsu.fcai.stockmanagementsystem.service.entities;

import eg.edu.bsu.fcai.stockmanagementsystem.model.Type;
import eg.edu.bsu.fcai.stockmanagementsystem.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeService {
    private final TypeRepository repository;

    public Type add(Type permissionDocument) {
        return repository.save(permissionDocument);
    }

    public List<Type> findAll() {
        return repository.findAll();
    }

    public Type findById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
