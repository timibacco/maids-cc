package core.maidscc.entity;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sales")
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Temporal(TemporalType.DATE)
    private LocalDate creationDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private ClientManagement client;

    @ManyToOne(cascade =  CascadeType.ALL)
    private UserManagement seller;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<ProductManagement> products;

    private int quantity;

    @Setter
    private double total;



    public double getTotal(){

        double totalPrice = 0;
        int totalQuantity = 0;

        for(ProductManagement p : products){
            totalPrice += p.getPrice();
            totalQuantity += quantity;
            total += totalPrice * totalQuantity;

        }
        return total;
    }

}
