package eg.edu.bsu.fcai.stockmanagementsystem.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MainGetDetails {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private MainGetPermission permission;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ItemStatus status;
    private String note;
    private Integer number;
}