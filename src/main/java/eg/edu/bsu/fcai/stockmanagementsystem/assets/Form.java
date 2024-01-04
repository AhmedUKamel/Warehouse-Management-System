package eg.edu.bsu.fcai.stockmanagementsystem.assets;

import eg.edu.bsu.fcai.stockmanagementsystem.model.ItemStatus;
import eg.edu.bsu.fcai.stockmanagementsystem.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Form {
    private String userId;
    private Long stockId;
    private Long documentId;
    private Long permissionDocumentId;
    private Long productId;
    private Long statusId;
    private String writerId;
    private String note;
    private ItemStatus status;
    private Product product;
    private Integer group;
    private Integer number;
    private Double unitPrice;
    private Double totalPrice;
}
