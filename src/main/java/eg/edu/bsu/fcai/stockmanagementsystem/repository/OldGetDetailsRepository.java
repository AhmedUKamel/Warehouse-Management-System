package eg.edu.bsu.fcai.stockmanagementsystem.repository;

import eg.edu.bsu.fcai.stockmanagementsystem.model.OldGetDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.OldGetPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OldGetDetailsRepository extends JpaRepository<OldGetDetails, Long> {
    List<OldGetDetails> findAllByPermission(OldGetPermission permission);
}
