package core.maidscc.service;

import core.maidscc.dto.authLoginDTO;
import core.maidscc.dto.authRegDTO;
import core.maidscc.entity.UserManagement;
import core.maidscc.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Service;


public interface AuthService {

    UserManagement register(authRegDTO request) throws UserAlreadyExistsException;

    String login(authLoginDTO request) throws Exception;
}
