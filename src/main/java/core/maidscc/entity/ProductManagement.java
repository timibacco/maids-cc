package core.maidscc.entity;

import core.maidscc.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;


@Data
@RequiredArgsConstructor
@Entity
@Table(name = "product_management")
public class ProductManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String productName;
    private String productDescription;
    private String productCategory;
    private LocalDate creationDate;
    private int availableQuantity;
    private double price;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    
}
