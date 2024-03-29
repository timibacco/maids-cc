package core.maidscc.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * Data Transfer Object for authentication
 * For a new user to register
 */
@Data
public class authRegDTO {

    private String email;

    private String password;

    private String Nationality;

    private LocalDate DOB;

    private String profilePicLink;
}
