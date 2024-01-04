package eg.edu.bsu.fcai.stockmanagementsystem.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MainGetItems {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(nullable = false)
    private MainGetPermission permission;
}
