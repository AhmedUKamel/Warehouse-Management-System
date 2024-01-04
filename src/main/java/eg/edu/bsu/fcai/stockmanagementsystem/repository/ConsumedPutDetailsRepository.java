package eg.edu.bsu.fcai.stockmanagementsystem.repository;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumedPutDetailsRepository extends JpaRepository<ConsumedPutDetails, Long> {
    List<ConsumedPutDetails> findAllByPermission(ConsumedPutPermission permission);
    List<ConsumedPutDetails> findAllByPermission_From(ApplicationUser applicationUser);
}
