package eg.edu.bsu.fcai.stockmanagementsystem.repository;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Type;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldPutPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldPutDetailsRepository extends JpaRepository<OldPutDetails, Long> {
    List<OldPutDetails> findByPermission_Type(Type type);

    List<OldPutDetails> findByPermission(OldPutPermission permission);

    List<OldPutDetails> findByPermission_From(ApplicationUser applicationUser);
}
