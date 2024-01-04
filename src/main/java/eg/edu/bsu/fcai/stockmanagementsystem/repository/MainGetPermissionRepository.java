package eg.edu.bsu.fcai.stockmanagementsystem.repository;

import eg.edu.bsu.fcai.stockmanagementsystem.model.MainGetPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainGetPermissionRepository extends JpaRepository<MainGetPermission, Long> {
}
