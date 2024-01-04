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
public class OldGetDetails {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private OldGetPermission permission;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
    private Integer number;
}
