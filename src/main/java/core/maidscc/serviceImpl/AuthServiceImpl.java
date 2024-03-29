package core.maidscc.serviceImpl;

import core.maidscc.config.TokenService;
import core.maidscc.dto.authLoginDTO;
import core.maidscc.dto.authRegDTO;
import core.maidscc.entity.UserManagement;
import core.maidscc.exceptions.IncorrectDetailsException;
import core.maidscc.exceptions.InvalidEmailException;
import core.maidscc.exceptions.UserAlreadyExistsException;
import core.maidscc.repository.UserRepository;
import core.maidscc.service.AuthService;
import core.maidscc.utils.Helper;
import jakarta.annotation.Nonnull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private final UserRepository repo;

    @Autowired
    private final Helper helper;

    @Autowired
    private final TokenService jwt;

    @Autowired
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();


    public UserManagement register(@NonNull authRegDTO request) throws UserAlreadyExistsException{
        Optional<UserManagement> user = repo.findByEmail(request.getEmail());

        if (user.isPresent()) {
            throw new UserAlreadyExistsException();
        }
        UserManagement new_user = new UserManagement();
        new_user.setEmail(request.getEmail());
        new_user.setPassword(encoder.encode(request.getPassword()));
        new_user.setProfilePicLink(request.getProfilePicLink());
        repo.saveAndFlush(new_user);
        return new_user;

    }

    @Override
    public String login(@Nonnull authLoginDTO request) throws Exception  {

        var user = repo.findByEmail(request.getEmail());

        if (helper.isEmailValid(request.getEmail())){
            throw new InvalidEmailException();
        }
        if (user.isEmpty()) {
            throw new UsernameNotFoundException( "Wrong username or password!");

        }

        if (!helper.isPasswordCorrect(request.getPassword(), user.get().getPassword())){
            throw new IncorrectDetailsException();
        }

        else {
            return jwt.generateToken(user.get());

        }
    }


}
