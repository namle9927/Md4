package ra.md4.model.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(unique = true, length = 100)
    private String categoryName;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "bit")
    private Boolean status;

}
