package core.maidscc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "client_management")
public class ClientManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String clientName;
    private String email;
    private String lastName;
    private String phoneNumber;
    private String address;

    @OneToOne
    private UserManagement userProfile;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Sales> sales;
    

}
