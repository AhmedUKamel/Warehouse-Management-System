package eg.edu.bsu.fcai.stockmanagementsystem.repository;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MainGetDetailsRepository extends JpaRepository<MainGetDetails, Long> {
    List<MainGetDetails> findAllByPermission(MainGetPermission permission);
    List<MainGetDetails> findAllByPermission_To(ApplicationUser applicationUser);
}
