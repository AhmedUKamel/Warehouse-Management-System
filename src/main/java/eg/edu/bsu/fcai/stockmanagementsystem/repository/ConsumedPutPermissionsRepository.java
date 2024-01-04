package eg.edu.bsu.fcai.stockmanagementsystem.repository;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ConsumedPutPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumedPutPermissionsRepository extends JpaRepository<ConsumedPutPermission, Long> {
}
