package core.maidscc.controller;


import core.maidscc.dto.authLoginDTO;
import core.maidscc.dto.authRegDTO;
import core.maidscc.entity.UserManagement;
import core.maidscc.exceptions.IncorrectDetailsException;
import core.maidscc.exceptions.InvalidEmailException;
import core.maidscc.repository.UserRepository;
import core.maidscc.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private final AuthService authService;



    /**
     * This method registers a new user
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody authRegDTO request) {
        try {
            var response = authService.register(request);
            return ResponseEntity.ok().body(response);

        } catch (Exception e) {
            log.error("Error while registering: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /**
     * This method logs in a user
     * And returns a token at the header.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody authLoginDTO request) throws Exception {
        try
        {

            String token = authService.login(request);
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, "Bearer "
                            + token)
                    .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
                            "Authorization")
                    .build();


        } catch (InvalidEmailException | IncorrectDetailsException | UsernameNotFoundException e) {
            log.error("Error while logging in: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }


}


