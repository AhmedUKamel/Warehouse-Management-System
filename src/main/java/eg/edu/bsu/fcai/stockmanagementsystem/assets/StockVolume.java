package eg.edu.bsu.fcai.stockmanagementsystem.assets;

import eg.edu.bsu.fcai.stockmanagementsystem.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockVolume {
    private Product product;
    private Long number;

    public void incrementBy(Long number) {
        this.number += number;
    }

    public void decrementBy(Long number) {
        this.number -= number;
    }
}
