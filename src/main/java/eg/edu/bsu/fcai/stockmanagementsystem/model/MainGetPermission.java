package eg.edu.bsu.fcai.stockmanagementsystem.model;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Stock;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MainGetPermission {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ApplicationUser to;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Stock from;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ApplicationUser writer;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ApplicationUser stockAdmin;
    private String note;
    private Date date;
}
