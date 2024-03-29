package core.maidscc.entity;

import lombok.Data;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;


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
    private String status;

    
}
