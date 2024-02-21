package ra.md4.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    @Column(unique = true, length = 100)
    private String productName;
    @Column(columnDefinition = "text")
    private String description;
    private BigDecimal unitPrice;
    private Integer stock;
    private String productUrl;
    @Column(columnDefinition = "bit")
    private Boolean status;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}

