package eg.edu.bsu.fcai.stockmanagementsystem.model;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ApplicationUser;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Type;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Stock;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumedPutPermission {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ApplicationUser from;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Stock to;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Type type;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ApplicationUser writer;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ApplicationUser stockAdmin;
    private Date date;
    private Integer groupNumber;
    private Double price;
    private String note;
}
