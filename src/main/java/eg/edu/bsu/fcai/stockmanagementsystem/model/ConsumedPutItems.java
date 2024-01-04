package eg.edu.bsu.fcai.stockmanagementsystem.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsumedPutItems {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Product product;
    @ManyToOne
    @JoinColumn(nullable = false)
    private ConsumedPutPermission permission;
}

