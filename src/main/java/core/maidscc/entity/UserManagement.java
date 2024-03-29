package core.maidscc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;


@Entity
@RequiredArgsConstructor
@Data
@Table(name = "users")
public class UserManagement implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(unique = true, nullable = false)
    private String email;

    private String profilePicLink;

    private String Nationality;

    private LocalDate DOB;

    private String roles;

    @JsonIgnore
    private String password;

    @JsonIgnore()
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore()
    @Override
    public String getPassword() {
        return password;
    }

    @JsonIgnore()
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore()
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonIgnore()
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonIgnore()
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @JsonIgnore()
    @Override
    public boolean isEnabled() {
        return false;
    }
}
