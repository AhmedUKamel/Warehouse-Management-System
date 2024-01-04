package eg.edu.bsu.fcai.stockmanagementsystem.repository;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.ItemStatus;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Type;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainPutDetails;
import eg.edu.bsu.fcai.stockmanagementsystem.model.MainPutPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MainPutDetailsRepository extends JpaRepository<MainPutDetails, Long> {
    List<MainPutDetails> findByPermission(MainPutPermission permission);

    List<MainPutDetails> findByStatusAndPermission_From(ItemStatus status, ApplicationUser applicationUser);

    List<MainPutDetails> findAllByPermission_From(ApplicationUser applicationUser);

    List<MainPutDetails> findAllByPermission_Type(Type type);
    List<MainPutDetails> findAllByPermission_FromAndPermission_Type(ApplicationUser applicationUser, Type type);

    @Query(value = "" +
            "SELECT mpd " +
            "FROM MainPutDetails mpd, MainPutPermission mpp " +
            "WHERE mpd.permission=mpp " +
            "AND mpp.type<>?1")
    List<MainPutDetails> findAllByNotPermission_Type(Type type);
    @Query(value = "" +
            "SELECT mpd " +
            "FROM MainPutDetails mpd, MainPutPermission mpp " +
            "WHERE mpd.permission=mpp " +
            "AND mpp.from=?1 " +
            "AND mpp.type<>?2")
    List<MainPutDetails> findAllByUserAndNotPermission_Type(ApplicationUser applicationUser, Type type);
}
